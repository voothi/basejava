package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.NotExistStorageException;
import ru.voothi.webapp.model.ContactType;
import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;


    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.<Void>execute("" +
                        "insert into resume (uuid, full_name) " +
                        "values (?, ?)",
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                    return null;
                });
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
                        final String value = rs.getString("value");
                        final ContactType type = ContactType.valueOf(rs.getString("type"));
                        resume.addContact(type, value);
                    } while (rs.next());
                    return resume;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("" +
                        "update resume set full_name = ? " +
                        "where uuid = ?",
                ps -> {
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getUuid());
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(resume.getUuid());
                    }
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
                }
        );
    }

    @Override
    public void clear() {
        sqlHelper.execute("" +
                "delete from resume");
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("" +
                        "select * from resume r " +
                        "order by full_name,uuid",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    List<Resume> resumes = new ArrayList<>();
                    while (rs.next()) {
                        resumes.add(new Resume(rs.getString("uuid"),
                                rs.getString("full_name")));
                    }
                    return resumes;
                });
    }
}
