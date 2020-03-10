package ru.voothi.webapp.storage;

import ru.voothi.webapp.storage.serializer.JsonStreamSerializer;

import javax.xml.bind.JAXBException;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() throws JAXBException {
        super(new PathStorage(STORAGEDIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}