package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.NotExistStorageException;
import ru.voothi.webapp.model.ContactType;
import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.model.Section;
import ru.voothi.webapp.model.SectionType;
import ru.voothi.webapp.sql.ConnectionFactory;
import ru.voothi.webapp.sql.SqlHelper;
import ru.voothi.webapp.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;


    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
                    try (PreparedStatement ps =
                                 connection.prepareStatement("" +
                                         "insert into resume (uuid, full_name) " +
                                         "VALUES (?, ?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    insertContact(connection, resume);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "select * from resume r " +
                        "left join contact c " +
                        "on r.uuid = c.resume_uuid " +
                        "where r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(rs, resume);
                    } while (rs.next());
                    return resume;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps =
                         connection.prepareStatement("" +
                                 "UPDATE resume SET full_name = ? " +
                                 "WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(connection, resume);
            insertContact(connection, resume);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("" +
                        "delete from resume " +
                        "where uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public int size() {
        return sqlHelper.execute("" +
                        "select count(*) from resume",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    return rs.next() ? rs.getInt(1) : 0;
                });
    }

    @Override
    public void clear() {
        sqlHelper.execute("" +
                "delete from resume");
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();

            try (PreparedStatement ps = conn.prepareStatement("select * from resume order by full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("select * from contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString("resume_uuid"));
                    addContact(rs, resume);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("select * from section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addSection(rs, r);
                }
            }

            return new ArrayList<>(resumes.values());
        });
    }

    private void insertContact(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps =
                     connection.prepareStatement("" +
                             "insert into contact (resume_uuid, type, value) " +
                             "VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection connection, Resume resume) {
        sqlHelper.execute("" +
                        "delete from contact " +
                        "where resume_uuid = ?",
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.execute();
                    return null;
                });
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            resume.addSection(type, JsonParser.read(content, Section.class));
        }
    }
}
