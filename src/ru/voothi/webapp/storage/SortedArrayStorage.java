package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Resume " + uuid + " present");
        }
        if (index < 0) {
            int insert = -index - 1;
            if (insert == 0) {
                System.out.println("Insert in fist, index " + index + " insert " + insert);
                storage[insert] = resume;
                size++;
            } else if (insert == size) {
                System.out.println("Insert in end, index " + index + " insert " + insert);
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Insert in mid, index " + index + " insert " + insert);
                int length = (size + 1) - (insert + 1);
                System.arraycopy(storage, insert, storage, insert + 1, length);
                storage[insert] = null;
                storage[insert] = resume;
                size++;
            }
        }
    }

    // get() in abstract class

    public void update(Resume resume) {
    }

    public void delete(String uuid) {
    }

    public void clear() {
    }

    // size() in abstract class

    public Resume[] getAll() {
        return new Resume[0];
    }

    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}