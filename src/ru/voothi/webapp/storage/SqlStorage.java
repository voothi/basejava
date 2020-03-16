package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.List;

public class SqlStorage implements Storage {
    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }
}
