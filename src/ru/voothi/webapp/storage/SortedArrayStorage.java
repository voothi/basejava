package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void deleteByIndex(int index) {
        int length = size - (index + 1);
        System.arraycopy(storage, index + 1, storage, index, length);
        storage[size - 1] = null;
        size--;
    }
}