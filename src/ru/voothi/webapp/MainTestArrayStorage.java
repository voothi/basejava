package ru.voothi.webapp;

import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.storage.SortedArrayStorage;

public class MainTestArrayStorage {
    private static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r1new = new Resume();
        r1new.setUuid("uuid1");

        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r2new = new Resume();
        r2new.setUuid("uuid2");

        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        Resume r5 = new Resume();
        r5.setUuid("uuid5");

        {
            System.out.println("----------------------------");
            System.out.println("Test save(), get(), size(), printAll methods");
            System.out.println("----------------------------");

            ARRAY_STORAGE.save(r1);
            ARRAY_STORAGE.save(r2);
            ARRAY_STORAGE.save(r3);
            ARRAY_STORAGE.save(r4);
            ARRAY_STORAGE.save(r5);

            System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
            System.out.println("Get dummy test:");
            System.out.println("dummy: " + ARRAY_STORAGE.get("dummy"));
            System.out.println("\nSize: " + ARRAY_STORAGE.size() + "\n");
            printAll();
        }

        {
            System.out.println("----------------------------");
            System.out.println("Test update() method");
            System.out.println("----------------------------");
            String testUuid = r2new.getUuid();
            System.out.println("Get resume " + testUuid + ": " + ARRAY_STORAGE.get(testUuid));
            System.out.println("Update resume: ");
            ARRAY_STORAGE.update(r2new);
            printAll();
        }

        {
            System.out.println("----------------------------");
            System.out.println("Test delete() method");
            System.out.println("----------------------------");
            ARRAY_STORAGE.delete(r1.getUuid());
            printAll();
        }

        {
            System.out.println("----------------------------");
            System.out.println("Test clear() method");
            System.out.println("----------------------------");
            ARRAY_STORAGE.clear();
            printAll();
            System.out.println("Size: " + ARRAY_STORAGE.size());
        }
    }

    private static void printAll() {
        Resume[] resumes = ARRAY_STORAGE.getAll();
        System.out.println("Print All Resumes from Storage:");
        if (resumes.length == 0) {
            System.out.println("Storage is Empty");
        } else {
            for (Resume element : resumes) {
                System.out.println(element.toString());
            }
        }
    }
}