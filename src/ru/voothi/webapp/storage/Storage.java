package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

public interface Storage {
    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    int size();

    void clear();

    Resume[] getAll();
}