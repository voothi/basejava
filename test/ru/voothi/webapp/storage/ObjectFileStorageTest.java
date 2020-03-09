package ru.voothi.webapp.storage;

import ru.voothi.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGEDIR, new ObjectStreamSerializer()));
    }
}