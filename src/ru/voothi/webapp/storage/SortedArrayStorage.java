package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        if (size >= LENGTH) {
            System.out.println("There is no space in the storage for the new entry.");
        } else {
            String uuid = resume.getUuid();
            int index = getIndex(uuid);
            if (index >= 0) {
                System.out.println("Resume " + uuid + " present");
            } else {
                int insert = -index - 1;
                if (insert == size) {
                    storage[size] = resume;
                    size++;
                } else {
                    int length = size - insert;
                    System.arraycopy(storage, insert, storage, insert + 1, length);
                    storage[insert] = null;
                    storage[insert] = resume;
                    size++;
                }
            }
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            int length = size - (index + 1);
            System.arraycopy(storage, index + 1, storage, index, length);
            size--;
        } else {
            System.out.println("Resume " + uuid + " not present");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}