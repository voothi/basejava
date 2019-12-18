package ru.voothi.webapp;

import ru.voothi.webapp.model.Resume;
import ru.voothi.webapp.storage.SortedArrayStorage;
import ru.voothi.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainArray {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume resume;
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | update uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    resume = new Resume(uuid);
                    ARRAY_STORAGE.save(resume);
                    printAll();
                    break;
                case "update":
                    resume = new Resume(uuid);
                    ARRAY_STORAGE.update(resume);
                    printAll();
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(uuid));
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
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