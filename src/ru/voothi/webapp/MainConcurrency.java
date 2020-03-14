package ru.voothi.webapp;

import java.util.concurrent.CountDownLatch;

public class MainConcurrency {
    public static final Object LOCK = new Object();
    public static final int THREADS_NUMBER = 10000;
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

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch countDownLatch = new CountDownLatch(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            final Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                countDownLatch.countDown();
            });
            thread.start();
        }

        countDownLatch.await();
        System.out.println(counter);
    }

    private synchronized void inc() {
        counter++;
    }
}