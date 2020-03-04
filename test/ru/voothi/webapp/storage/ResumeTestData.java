package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.*;

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
        itemsAchievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
                "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        itemsAchievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        itemsAchievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        itemsAchievement.add("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        sections.put(SectionType.ACHIEVEMENT, new ListSection(itemsAchievement));

        List<String> itemsQualification = new ArrayList<>();
        itemsQualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        itemsQualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        itemsQualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        itemsQualification.add("MySQL, SQLite, MS SQL, HSQLDB");
        itemsQualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        itemsQualification.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        itemsQualification.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, " +
                "MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, " +
                "Eclipse SWT, JUnit, Selenium (htmlelements).");
        itemsQualification.add("Python: Django.");
        itemsQualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        itemsQualification.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        itemsQualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, " +
                "JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, " +
                "SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        itemsQualification.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        itemsQualification.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, " +
                "Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        itemsQualification.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        itemsQualification.add("Родной русский, английский \"upper intermediate\"");
        sections.put(SectionType.QUALIFICATION, new ListSection(itemsQualification));


//        List<Organization> listOrganizationsExperience = new ArrayList<>();
//        listOrganizationsExperience.add(new Organization("Java Online Projects", "http://javaops.ru/",
//                LocalDate.of(2013, 10, 1),
//                LocalDate.now(),
//                "Автор проекта.",
//                "Создание, организация и проведение Java онлайн проектов и стажировок."));
//        listOrganizationsExperience.add(new Organization("Wrike", "https://www.wrike.com/",
//                LocalDate.of(2014, 10, 1),
//                LocalDate.of(2016, 1, 1),
//                "Старший разработчик (backend)",
//                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
//                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
//                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
//        listOrganizationsExperience.add(new Organization("RIT Center", "",
//                LocalDate.of(2012, 4, 1),
//                LocalDate.of(2014, 10, 1),
//                "Java архитектор",
//                "Организация процесса разработки системы ERP для разных окружений: " +
//                        "релизная политика, версионирование, ведение CI (Jenkins), " +
//                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), " +
//                        "AAA via SSO. Архитектура БД и серверной части системы. " +
//                        "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
//                        "сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
//                        "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
//                        "Maven + plugin development, Ant, Apache Commons, Spring security, " +
//                        "Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, " +
//                        "Unix shell remote scripting via ssh tunnels, PL/Python"));
//        listOrganizationsExperience.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
//                LocalDate.of(2010, 12, 1),
//                LocalDate.of(2012, 4, 1),
//                "Ведущий программист",
//                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
//                        "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
//                        "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов " +
//                        "в области алгоритмического трейдинга. " +
//                        "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
//        listOrganizationsExperience.add(new Organization("Yota", "https://www.yota.ru/",
//                LocalDate.of(2008, 6, 1),
//                LocalDate.of(2010, 12, 1),
//                "Ведущий специалист",
//                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
//                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
//                        "Реализация администрирования, статистики и мониторинга фреймворка. " +
//                        "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
//        listOrganizationsExperience.add(new Organization("Enkata", "http://enkata.com/",
//                LocalDate.of(2007, 3, 1),
//                LocalDate.of(2008, 6, 1),
//                "Разработчик ПО",
//                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) " +
//                        "частей кластерного J2EE приложения (OLAP, Data mining)."));
//        listOrganizationsExperience.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
//                LocalDate.of(2005, 1, 1),
//                LocalDate.of(2007, 2, 1),
//                "Разработчик ПО",
//                "Разработка информационной модели, проектирование интерфейсов, " +
//                        "реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
//        listOrganizationsExperience.add(new Organization("Alcatel", "http://www.alcatel.ru/",
//                LocalDate.of(1997, 9, 1),
//                LocalDate.of(2005, 1, 1),
//                "Инженер по аппаратному и программному тестированию",
//                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
//        sections.put(SectionType.EXPERIENCE, new OrganizationSection(listOrganizationsExperience));
//
//        List<Organization> listOrganizationsEducation;
//        listOrganizationsEducation = new ArrayList<>();
//        listOrganizationsEducation.add(new Organization("Coursera", "https://www.coursera.org/course/progfun",
//                LocalDate.of(2013, 3, 1),
//                LocalDate.of(2013, 5, 1),
//                "Coursera",
//                "\"Functional Programming Principles in Scala\" by Martin Odersky"));
//        listOrganizationsEducation.add(new Organization("Luxoft", "http://www.luxoft.ru/",
//                LocalDate.of(2011, 3, 1),
//                LocalDate.of(2011, 4, 1),
//                "",
//                ""));
//        listOrganizationsEducation.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
//                LocalDate.of(2005, 1, 1),
//                LocalDate.of(2005, 4, 1),
//                "",
//                ""));
//        listOrganizationsEducation.add(new Organization("Alcatel", "http://www.alcatel.ru/",
//                LocalDate.of(1997, 3, 1),
//                LocalDate.of(1998, 5, 1),
//                "",
//                ""));
//        listOrganizationsEducation.add(new Organization("Санкт-Петербургский национальный исследовательский " +
//                "университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
//                LocalDate.of(1993, 9, 1),
//                LocalDate.of(1996, 7, 1),
//                "",
//                ""));
//        listOrganizationsEducation.add(new Organization("Санкт-Петербургский национальный исследовательский " +
//                "университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
//                LocalDate.of(1987, 9, 1),
//                LocalDate.of(1993, 7, 1),
//                "",
//                ""));
//        listOrganizationsEducation.add(new Organization("Заочная физико-техническая школа при МФТИ",
//                "http://www.school.mipt.ru/",
//                LocalDate.of(1984, 9, 1),
//                LocalDate.of(1987, 6, 1),
//                "Закончил с отличием",
//                ""));
//        sections.put(SectionType.EDUCATION, new OrganizationSection(listOrganizationsEducation));
//        System.out.println(sections);
    }
}
