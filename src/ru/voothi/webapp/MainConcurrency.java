package ru.voothi.webapp;

import java.util.concurrent.*;

public class MainConcurrency {
    public static final Object LOCK = new Object();
    public static final int THREADS_NUMBER = 10000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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
        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<?> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                countDownLatch.countDown();
            });
            future.isDone();
            future.get();
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(counter);
    }

    private synchronized void inc() {
        counter++;
    }
}