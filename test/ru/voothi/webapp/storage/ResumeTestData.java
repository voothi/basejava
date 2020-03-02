package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        Map<ContactType, String> contacts = resume.getContacts();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");
        System.out.println(contacts);

        Map<SectionType, Section> sections = resume.getSections();
        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));


        List<String> itemsAchievement = new ArrayList<>();
        itemsAchievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        itemsAchievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        sections.put(SectionType.ACHIEVEMENT, new ListSection(itemsAchievement));

        List<String> itemsQualification = new ArrayList<>();
        itemsQualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        itemsQualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        sections.put(SectionType.QUALIFICATION, new ListSection(itemsQualification));


        List<Organization> listOrganizationsExperience;
        listOrganizationsExperience = new ArrayList<>();
        listOrganizationsExperience.add(new Organization("Java Online Projects", "http://javaops.ru/",
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        listOrganizationsExperience.add(new Organization("Wrike", "https://www.wrike.com/",
                LocalDate.of(2014, 10, 01),
                LocalDate.of(2016, 01, 01),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        sections.put(SectionType.EXPERIENCE, new OrganizationSection(listOrganizationsExperience));

        List<Organization> listOrganizationsEducation;
        listOrganizationsEducation = new ArrayList<>();
        listOrganizationsEducation.add(new Organization("Coursera", "https://www.coursera.org/course/progfun",
                LocalDate.of(2013, 03, 1),
                LocalDate.of(2013, 05, 1),
                "Coursera",
                "\"Functional Programming Principles in Scala\" by Martin Odersky"));
        sections.put(SectionType.EDUCATION, new OrganizationSection(listOrganizationsEducation));
        System.out.println(sections);
    }
}
