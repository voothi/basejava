package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int LENGTH = 10_000;
    protected Resume[] storage = new Resume[LENGTH];
    protected int size;

    @Override
    protected void doSave(Resume resume, Integer index) {
        String uuid = resume.getUuid();
        if (size >= LENGTH) {
            throw new StorageException("There is no space in the storage for the new entry.", uuid);
        } else {
            insertByIndex(resume, index);
            size++;
        }
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    public void doDelete(Integer index) {
        deleteByIndex(index);
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

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void deleteByIndex(int index);

    protected abstract void insertByIndex(Resume resume, int index);
}