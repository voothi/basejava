package ru.voothi.webapp;

public class MainConcurrency {
    private static int counter;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        final Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();

        System.out.println(thread0.getState());

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter++;
                }
            }).start();
        }

        System.out.println(counter);
    }
}
