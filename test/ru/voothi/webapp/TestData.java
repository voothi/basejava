package ru.voothi.webapp;

import ru.voothi.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.setContact(ContactType.PHONE, "+7(921) 111-1111");
        RESUME_1.setContact(ContactType.SKYPE, "skype.new");
        RESUME_1.setContact(ContactType.EMAIL, "email@yandex.ru");
        RESUME_1.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME_1.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME_1.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        RESUME_1.setContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        RESUME_1.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок"));
        RESUME_1.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума"));
        RESUME_1.setSection(SectionType.ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов",
                "Реализация двухфакторной аутентификации"));
        RESUME_1.setSection(SectionType.QUALIFICATION, new ListSection("JEE AS: GlassFish",
                "Version control:"));
        RESUME_1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects", "http://javaops.ru/",
                                new Organization.Position(
                                        2013, Month.OCTOBER,
                                        "Автор проекта.", "Создание, организация"))));
        RESUME_1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Санкт-Петербургский национальный исследовательский университет " +
                                "информационных технологий, механики и оптики",
                                "http://www.ifmo.ru/",
                                new Organization.Position(
                                        1993, Month.SEPTEMBER,
                                        1996, Month.JULY,
                                        "Аспирантура (программист С, С++)", ""),
                                new Organization.Position(
                                        1987, Month.SEPTEMBER,
                                        1993, Month.JULY,
                                        "Инженер (программист Fortran, C)", ""))));

        RESUME_2.setContact(ContactType.PHONE, "+7(999) 999-99999");
        RESUME_2.setContact(ContactType.SKYPE, "skype_contact2");
    }
}
