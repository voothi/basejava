package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage implements Storage {
    private static final int LENGTH = 10_000;
    private Resume[] storage = new Resume[LENGTH];
    private int size;

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Not present");
        }
    }

    public void save(Resume resume) {
        if (size >= LENGTH) {
            System.out.println("There is no space in the storage for the new entry.");
        } else if (getIndex(resume.getUuid()) == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Resume " + resume.getUuid() + "present");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + "not present");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + "not present");
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}