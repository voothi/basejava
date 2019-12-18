package ru.voothi.webapp.storage;

import ru.voothi.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(resume);
        field.get(resume);
        field.set(resume, "new uuid");
        System.out.println(resume);
    }
}