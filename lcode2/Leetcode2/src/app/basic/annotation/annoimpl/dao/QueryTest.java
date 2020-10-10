package app.basic.annotation.annoimpl.dao;

import app.Timer;
import app.basic.annotation.annoimpl.domain.User;

public class QueryTest {
    public static void main(String[] args) {
        User userTable = new User();
        userTable.setName("Jack");
        userTable.setAge(18);
        userTable.setEmail("asdf@ff.com, dda@qu.com, da@cc.com");

        Dept dept = new Dept();
        dept.setDeptId(10);
        dept.setDeptName("CS Department");
        DataBaseAccessor dba = new DataBaseAccessor();
        String stmt = (String) Timer.execute(dba, "query", new Object[] {userTable}, 
                                    Object.class);
        System.out.println(stmt);
        System.out.println(dba.query(dept));
    }
}
