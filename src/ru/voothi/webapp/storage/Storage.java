package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.List;

public interface Storage {
    void save(Resume resume);

    Resume get(String uuid);

    void update(Resume resume);

    void delete(String uuid);

    int size();

    void clear();

    List<Resume> getAllSorted();
}