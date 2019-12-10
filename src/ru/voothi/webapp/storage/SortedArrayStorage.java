package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
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

    public void update(Resume resume) {
    }

    public void delete(String uuid) {
    }

    public void clear() {
    }

    public Resume[] getAll() {
        return new Resume[0];
    }

    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }
}