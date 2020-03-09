package ru.voothi.webapp.storage;

import ru.voothi.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamSerializer(STORAGEDIR));
    }
}