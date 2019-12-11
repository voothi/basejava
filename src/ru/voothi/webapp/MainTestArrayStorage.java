package ru.voothi.webapp;

import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.storage.SortedArrayStorage;

public class MainTestArrayStorage {
    private static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        Resume r1new = new Resume();
        r1new.setUuid("uuid1");
        r2.setUuid("uuid2");
        Resume r2new = new Resume();
        r2new.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

//        ARRAY_STORAGE.storage[0] = r1;
//        ARRAY_STORAGE.storage[1] = r2;
        ARRAY_STORAGE.size = 0;

//        {
//            System.out.println("----------------------------");
//            System.out.println("Test save(), get(), size(), printAll methods");
//            System.out.println("----------------------------");

//            ARRAY_STORAGE.save(r1);
            ARRAY_STORAGE.save(r1new);
//            ARRAY_STORAGE.save(r3);

//            System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
//            System.out.println("Get dummy test:");
//            System.out.println("dummy: " + ARRAY_STORAGE.get("dummy"));
//
//            System.out.println("\nSize: " + ARRAY_STORAGE.size() + "\n");
//            printAll();
//        }
//
//        {
//            System.out.println("----------------------------");
//            System.out.println("Test update() method");
//            System.out.println("----------------------------");
//            System.out.println("New resume not present in storage\n" + r2new.getUuid());
//            System.out.println("Update " + r2new.getUuid() + " in storage...");
//            ARRAY_STORAGE.update(r2new);
//            System.out.println("Update sucessful");
//            printAll();
//        }
//
//        {
//            System.out.println("----------------------------");
//            System.out.println("Test delete() method");
//            System.out.println("----------------------------");
//            ARRAY_STORAGE.delete(r1.getUuid());
//            printAll();
//        }
//
//        {
//            System.out.println("----------------------------");
//            System.out.println("Test clear() method");
//            System.out.println("----------------------------");
//            ARRAY_STORAGE.clear();
//            printAll();
//            System.out.println("Size: " + ARRAY_STORAGE.size());
//        }
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