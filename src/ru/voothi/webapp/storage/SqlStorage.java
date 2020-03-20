package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SqlStorage implements Storage {
    ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        };
    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }
}
