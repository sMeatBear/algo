package app.basic.annotation.annoimpl.domain;

import app.basic.annotation.annoimpl.dao.ColumnName;
import app.basic.annotation.annoimpl.dao.TableName;

/**
 * User bean
 * ORM to db
 */
@TableName("user_table")
public class User {
    @ColumnName("user_name")
    public String name;
    @ColumnName("user_age")
    public Integer age;
    @ColumnName("user_email")
    public String email;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
