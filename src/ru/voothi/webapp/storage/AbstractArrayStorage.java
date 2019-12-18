package ru.voothi.webapp.storage;

import ru.voothi.webapp.exception.ExistStorageException;
import ru.voothi.webapp.exception.NotExistStorageException;
import ru.voothi.webapp.exception.StorageException;
import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int LENGTH = 10_000;
    protected Resume[] storage = new Resume[LENGTH];
    protected int size;

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (size >= LENGTH) {
            throw new StorageException("There is no space in the storage for the new entry.", uuid);
        } else if (index >= 0) {
            throw new ExistStorageException(uuid);
        } else {
            insertByIndex(resume, index);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteByIndex(index);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
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

    protected abstract int getIndex(String uuid);

    protected abstract void deleteByIndex(int index);

    protected abstract void insertByIndex(Resume resume, int index);
}