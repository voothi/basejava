package ru.voothi.webapp;

public class MainConcurrency {
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
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

        Object lock = new Object();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc(lock);
                }
            }).start();
        }

        Thread.sleep(500);

        System.out.println(counter);
    }

    private static void inc(Object lock) {
        double a = Math.sin(13.);
        synchronized (lock) {
            counter++;
        }
    }
}
