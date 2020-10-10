package app.basic.annotation.annoimpl.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataBaseAccessor implements Accessable {
    @Override
    public <T> String query(T domain) {
        Class<?> table = domain.getClass();
        // assemble sql statement
        StringBuilder stmt = new StringBuilder();
        stmt.append("select\s*\sfrom\s");

        // 1. get table name
        boolean tableNameExit = table.isAnnotationPresent(TableName.class);
        if (!tableNameExit) {
            return null;
        }
        TableName tn = table.getAnnotation(TableName.class);
        String tableName = tn.value();
        stmt.append(tableName).append("\swhere\s1=1");

        // 2. get all the column if it is not null
        for (Field f : table.getFields()) {
            boolean columnNameExit = f.isAnnotationPresent(ColumnName.class);
            if (!columnNameExit) {
                continue;
            }

            // get the column name
            String colName = f.getAnnotation(ColumnName.class).value();
            // get the column value
            String colValMethodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
            Object colVal = null;
            try {
                Method m = table.getMethod(colValMethodName);
                colVal = m.invoke(domain);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                continue;
            }

            if (colVal == null) {continue;}

            stmt.append("\sand\s").append(colName);
            if (colVal instanceof Integer) {stmt.append('=').append(colVal);}
            else if (colVal instanceof String) {
                String colValStr = (String) colVal;
                if (colValStr.contains(",")) {
                    String[] valOptions = colValStr.split(",");
                    stmt.append("\sin").append('(');
                    for (String val : valOptions) {
                        stmt.append('\'').append(val.trim()).append('\'').append(',');
                    }
                    stmt.deleteCharAt(stmt.length() - 1);
                    stmt.append(')');
                } else {
                    stmt.append('=').append('\'').append(colVal).append('\'');
                }
            }
        }

        return stmt.toString();
    }
}
