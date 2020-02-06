package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int LENGTH = 10_000;
    protected Resume[] storage = new Resume[LENGTH];
    protected int size;

    @Override
    protected void doSave(Resume resume, Object index) {
        String uuid = resume.getUuid();
        if (size >= LENGTH) {
            throw new StorageException("There is no space in the storage for the new entry.", uuid);
        } else {
            insertByIndex(resume, (Integer) index);
            size++;
        }
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    public void doDelete(Object index) {
        deleteByIndex((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void deleteByIndex(int index);

    protected abstract void insertByIndex(Resume resume, int index);
}