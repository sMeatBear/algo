package app.basic.annotation.annoimpl.dao;

import app.basic.annotation.annoimpl.domain.User;

public class Query {
    public static void main(String[] args) {
        User userTable = new User();
        userTable.setName("Jack");
        userTable.setAge(18);
        userTable.setEmail("asdf@ff.com, dda@qu.com, da@cc.com");
        DataBaseAccessor dba = new DataBaseAccessor();
        String stmt = dba.query(userTable);
        System.out.println(stmt);
    }
}
