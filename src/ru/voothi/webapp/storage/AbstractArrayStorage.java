package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    //    protected static final int LENGTH = 10_000;
    protected static final int LENGTH = 4;
    public Resume[] storage = new Resume[LENGTH];
    public int size;

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Resume " + uuid + " not present");
        }
        return null;
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}