package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertByIndex(Resume resume, int index) {
        if (index == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Resume " + resume.getUuid() + " present");
        }
    }

    @Override
    protected void deleteByIndex(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}