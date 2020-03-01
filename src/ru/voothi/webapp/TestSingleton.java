package ru.voothi.webapp;

public class TestSingleton {
    private static TestSingleton instance = new TestSingleton();

    private TestSingleton() {
    }

    public static TestSingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
    }
}
