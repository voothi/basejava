package ru.voothi.webapp;

import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.storage.ArrayStorage;

public class Util {
    public static void printTestSeparator() {
        System.out.println("----------------------------");
    }

    public static void printPresent(    ) {
        System.out.println("Present");
    }

    public static void printNotPresent() {
        System.out.println("Not present");
    }

    public static void printAll(ArrayStorage storage) {
        Resume[] resumes = storage.getAll();
        System.out.println("Print All Resumes from Storage:");
        if (resumes.length == 0) {
            System.out.println("Storage is Empty");
        } else {
            for (Resume resume : resumes) {
                System.out.println(resume.toString());
            }
        }
    }

    public static void printTestTitle(String title) {
        printTestSeparator();
        System.out.println(title);
        printTestSeparator();
    }
}
