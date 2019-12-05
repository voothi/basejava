package ru.voothi.webapp;

import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.storage.ArrayStorage;

import static ru.voothi.webapp.Util.printAll;
import static ru.voothi.webapp.Util.printTestTitle;

/**
 * Test for your ru.voothi.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {

        // Initialize the variable is for testing
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r2new = new Resume();
        r2new.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        // Test save(), get(), size(), printAll methods
        {
            printTestTitle("Test save(), get(), size(), printAll methods");

            ARRAY_STORAGE.save(r1);
            ARRAY_STORAGE.save(r2);
            ARRAY_STORAGE.save(r3);
            System.out.println("Save: OK");

            System.out.println("");
            System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
            System.out.println("Get dummy test:");
            System.out.println("dummy: " + ARRAY_STORAGE.get("dummy"));

            System.out.println("");
            System.out.println("Size: " + ARRAY_STORAGE.size());

            System.out.println("");
            printAll(ARRAY_STORAGE);
        }

        // Test update() method
        {
            printTestTitle("Test update() method");
            System.out.println("New resume not present in storage\n" + r2new.getUuid() + " | " + r2new.toStringSuper());
            System.out.println("Update " + r2new.getUuid() + " in storage...");
            ARRAY_STORAGE.update(r2new);
            System.out.println("Update sucessful");
            printAll(ARRAY_STORAGE);
        }

        // Test delete() method
        {
            printTestTitle("Test delete() method");
            ARRAY_STORAGE.delete(r1.getUuid());
            printAll(ARRAY_STORAGE);
        }

        // Test clear() method
        {
            printTestTitle("Test clear() method");

            ARRAY_STORAGE.clear();
            printAll(ARRAY_STORAGE);
            System.out.println("Size: " + ARRAY_STORAGE.size());
        }
    }
}