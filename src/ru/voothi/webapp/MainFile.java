package ru.voothi.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File file = new File("./.gitignore");
        System.out.println(file.getCanonicalPath());
        File dir = new File("./src/ru/voothi/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String fileName : list) {
                System.out.println(fileName);
            }
        }
    }
}
