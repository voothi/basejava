package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

import static ru.voothi.webapp.Util.*;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int LENGTH = 10_000;
    private Resume[] storage = new Resume[LENGTH];;
    private int size;

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            storage[index] = resume;
        } else {
            printNotPresent();
        }
    }

    public void save(Resume resume) {
        if (size >= LENGTH) {
            System.out.println("There is no space in the storage for the new entry.");
        } else if (getIndex(resume.getUuid()) == -1) {
            storage[size] = resume;
            size++;
        } else {
            printPresent();
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) > -1) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return storage[i];
                }
            }
        } else {
            printNotPresent();
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        if (size > 0) {
            storage[size - 1] = null;
            size--;
        } else {
            printNotPresent();
        }
    }

    /**
     * getSize
     */
    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage,0 , size,null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    /**
     * If logical present resume or not present
     *
     * @return -1 Not present, > -1 Present
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}