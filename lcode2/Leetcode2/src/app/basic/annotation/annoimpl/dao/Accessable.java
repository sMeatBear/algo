package app.basic.annotation.annoimpl.dao;

public interface Accessable {
    <T> String query(T domain);
}
