package ru.voothi.webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.LENGTH; i++) {
                storage.save(new Resume("name" + i));
            }
        } catch (StorageException e) {
            Assert.fail(e.getMessage());
        }
        storage.save(new Resume("Overload"));
    }
}