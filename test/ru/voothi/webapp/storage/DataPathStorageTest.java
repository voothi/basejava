package ru.voothi.webapp.storage;

import ru.voothi.webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGEDIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}