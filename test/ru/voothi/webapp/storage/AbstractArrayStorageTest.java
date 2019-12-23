package ru.voothi.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.voothi.webapp.exception.NotExistStorageException;
import ru.voothi.webapp.model.Resume;

public class AbstractArrayStorageTest {
    private static final Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void save() {
    }

    @Test
    public void get() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
    }

    @Test
    public void getAll() {
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}