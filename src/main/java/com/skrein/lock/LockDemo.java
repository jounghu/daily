package com.skrein.lock;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: hujiansong
 * @Date: 2019/6/6 17:45
 * @since: 1.8
 */
public class LockDemo {

    private static Lock lock = new ReentrantLock();


    static class Works implements Runnable {

        private CountDownLatch countDownLatch;

        private int i;

        public Works(CountDownLatch countDownLatch, int i) {
            this.countDownLatch = countDownLatch;
            this.i = i;
        }

        @Override
        public void run() {
            lock.lock();
            countDownLatch.countDown();
//            lock.unlock();
            System.out.println(this.i+"--countDown");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new ThreadFactory() {
            private AtomicLong atomicLong = new AtomicLong(10);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName(atomicLong.getAndIncrement() + "-thread");
                return thread;
            }
        });

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Works(countDownLatch, i));
        }
        countDownLatch.await();

        System.out.println("main is finish");
    }


}
