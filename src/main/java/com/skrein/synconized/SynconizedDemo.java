package com.skrein.synconized;

/**
 * @Author: hujiansong
 * @Date: 2019/5/20 16:08
 * @since: 1.8
 */
public class SynconizedDemo {

    public static synchronized void doMethodA() {
        System.out.println("1111");
    }

    public synchronized void methodB() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("2222");
    }

    public static void main(String[] args) {
        SynconizedDemo synconizedDemo = new SynconizedDemo();
        Thread thread1 = new MyThread(synconizedDemo);
        thread1.start();


        Thread thread2 = new MyThread1();
        thread2.start();


        Thread thread3 = new MyThread(synconizedDemo);
        thread3.start();

    }

    static class MyThread extends Thread {
        SynconizedDemo synconizedDemo;

        public MyThread(SynconizedDemo synconizedDemo) {
            this.synconizedDemo = synconizedDemo;
        }

        @Override
        public void run() {
            try {
                synconizedDemo.methodB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThread1 extends Thread {

        @Override
        public void run() {
            SynconizedDemo.doMethodA();
        }
    }
}
