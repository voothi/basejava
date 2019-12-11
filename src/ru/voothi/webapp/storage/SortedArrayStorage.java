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
                if (insert == 0 && (storage[insert + 1] == null)) {
                    storage[insert] = resume;
                    size++;
                } else if (insert == size) {
                    storage[size] = resume;
                    size++;
                } else {
                    int length = (size + 1) - (insert + 1);
                    System.arraycopy(storage, insert, storage, insert + 1, length);
                    storage[insert] = null;
                    storage[insert] = resume;
                    size++;
                }
            }
        }
    }

    // get() in abstract class

    // update() in abstract class

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            int length = ((size + 1) - 1) - (index + 1);
            System.arraycopy(storage, index + 1, storage, index, length);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume " + uuid + " not present");
        }
    }

    // size() in abstract class

    // clear() in abstract class

    // getAll() in abstract class

    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}