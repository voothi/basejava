package ru.voothi.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency {
    public static final Object LOCK = new Object();
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    public static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

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
        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println(mainConcurrency.atomicInteger.get());
    }

    private void inc() {
        atomicInteger.incrementAndGet();
        counter++;
    }
}