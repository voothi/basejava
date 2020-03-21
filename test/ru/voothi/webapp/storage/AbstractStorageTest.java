package ru.voothi.webapp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.voothi.webapp.Config;
import ru.voothi.webapp.exception.ExistStorageException;
import ru.voothi.webapp.exception.NotExistStorageException;
import ru.voothi.webapp.model.ContactType;
import ru.voothi.webapp.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractStorageTest {
    protected static final File STORAGEDIR = Config.getInstance().getStorageDir();
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

//        RESUME_1.addContact(ContactType.PHONE, "+7(921) 855-0482");
//        RESUME_1.addContact(ContactType.SKYPE, "grigory.kislin");
//        RESUME_1.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
//        RESUME_1.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
//        RESUME_1.addContact(ContactType.GITHUB, "https://github.com/gkislin");
//        RESUME_1.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
//        RESUME_1.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");
//        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок"));
//        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума"));
//        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов",
//                "Реализация двухфакторной аутентификации"));
//        RESUME_1.addSection(SectionType.QUALIFICATION, new ListSection("JEE AS: GlassFish",
//                "Version control:"));
//        RESUME_1.addSection(SectionType.EXPERIENCE,
//                new OrganizationSection(
//                        new Organization("Java Online Projects", "http://javaops.ru/",
//                                new Organization.Position(
//                                        2013, Month.OCTOBER,
//                                        "Автор проекта.", "Создание, организация"))));
//        RESUME_1.addSection(SectionType.EDUCATION,
//                new OrganizationSection(
//                        new Organization("Санкт-Петербургский национальный исследовательский университет " +
//                                "информационных технологий, механики и оптики",
//                                "http://www.ifmo.ru/",
//                                new Organization.Position(
//                                        1993, Month.SEPTEMBER,
//                                        1996, Month.JULY,
//                                        "Аспирантура (программист С, С++)", ""),
//                                new Organization.Position(
//                                        1987, Month.SEPTEMBER,
//                                        1993, Month.JULY,
//                                        "Инженер (программист Fortran, C)", ""))));

        RESUME_2.addContact(ContactType.PHONE, "+7(999) 999-99999");
        RESUME_2.addContact(ContactType.SKYPE, "skype_contact2");
    }

    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume resume1new = new Resume(UUID_1, "fullName1");
        storage.update(resume1new);
        assertTrue(resume1new.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("dummy", "dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}