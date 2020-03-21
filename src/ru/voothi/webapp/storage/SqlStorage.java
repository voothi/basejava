package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.NotExistStorageException;
import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.sql.ConnectionFactory;
import ru.voothi.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;


    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume resume) {
        try (final Connection connection = connectionFactory.getConnection();
             final PreparedStatement ps =
                     connection.prepareStatement("insert into resume (uuid, full_name) values (?, ?)")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try (final Connection connection = connectionFactory.getConnection();
             final PreparedStatement ps = connection.prepareStatement("select * from resume where uuid = ?")) {
            ps.setString(1, uuid);
            final ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
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
        sqlHelper.execute("delete from resume");
    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }
}
