package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected Integer getSearchKey(String uuid) {
        Resume resume = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void deleteByIndex(int index) {
        int length = size - (index + 1);
        System.arraycopy(storage, index + 1, storage, index, length);
    }

    @Override
    protected void insertByIndex(Resume resume, int index) {
        int insert = -index - 1;
        if (insert == size) {
            storage[size] = resume;
        } else {
            int length = size - insert;
            System.arraycopy(storage, insert, storage, insert + 1, length);
            storage[insert] = resume;
        }
    }
}