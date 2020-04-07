package ru.voothi.webapp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.voothi.webapp.Config;
import ru.voothi.webapp.exception.ExistStorageException;
import ru.voothi.webapp.exception.NotExistStorageException;
import ru.voothi.webapp.model.ContactType;
import ru.voothi.webapp.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static ru.voothi.webapp.TestData.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGEDIR = Config.getInstance().getStorageDir();

    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume resume1new = new Resume(UUID_1, "New Resume1");
        RESUME_1.setContact(ContactType.PHONE, "+7(921) 855-0482");
        RESUME_1.setContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_1.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        storage.update(resume1new);
        assertTrue(resume1new.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy", "dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(Arrays.asList(RESUME_1, RESUME_2, RESUME_3), list);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}