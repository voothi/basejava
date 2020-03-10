package ru.voothi.webapp.storage;

import ru.voothi.webapp.storage.serializer.XmlStreamSerializer;

import javax.xml.bind.JAXBException;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() throws JAXBException {
        super(new PathStorage(STORAGEDIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}