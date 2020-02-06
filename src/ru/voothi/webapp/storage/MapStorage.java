package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }
}
