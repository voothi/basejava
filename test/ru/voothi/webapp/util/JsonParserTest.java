package ru.voothi.webapp.util;

import org.junit.Assert;
import org.junit.Test;
import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.model.Section;
import ru.voothi.webapp.model.TextSection;
import ru.voothi.webapp.storage.ArrayStorageTest;

import static ru.voothi.webapp.TestData.*;

public class JsonParserTest extends ArrayStorageTest {

    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}