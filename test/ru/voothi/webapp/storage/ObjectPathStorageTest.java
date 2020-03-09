package ru.voothi.webapp.storage;

import ru.voothi.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGEDIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}