package ru.voothi.webapp.storage;

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
            System.out.println("There is no space in the storage for the new entry.");
        } else {
            int insert = -index - 1;
            if (index >= 0) {
                System.out.println("Resume " + resume.getUuid() + " present");
            } else if (index == -1) {
                insertByIndex(resume, size);
            } else {
                if (insert == size) {
                    insertByIndex(resume, insert);
                } else {
                    int length = size - insert;
                    System.arraycopy(storage, insert, storage, insert + 1, length);
                    insertByIndex(resume, insert);
                }
            }
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + " not present");
        }
        return null;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " not present");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteByIndex(index);
        } else {
            System.out.println("Resume " + uuid + " not present");
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

    protected void insertByIndex(Resume resume, int index) {
        storage[size] = resume;
        size++;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void deleteByIndex(int index);
}