package com.skrein.synconized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: hujiansong
 * @Date: 2019/5/20 16:32
 * @since: 1.8
 */
public class BlockingQueueDemo {
    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();


    static class Producer implements Runnable {

        private LinkedBlockingQueue<String> queue;

        public Producer(LinkedBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                this.queue.put(Thread.currentThread().getName() + "---");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Consumer implements Runnable {

        private LinkedBlockingQueue<String> queue;

        public Consumer(LinkedBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                String take = this.queue.take();
                System.out.println(Thread.currentThread().getName() + "--" + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Producer(queue));
        }

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Consumer(queue));
        }

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Producer(queue));
        }

        executorService.shutdown();
    }
}
