package com.skrein.printOdd;

/**
 * @author :hujiansong
 * @date :2019/6/25 18:02
 * @since :1.8
 */
public class PrintOdd {

    static class OddThread extends Thread {


        public void print(int i) {
            if (i % 2 == 0) {
                System.out.println(i);
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class JiThread {

        public void print(int i) {
            if (i % 2 != 0) {
                System.out.println(i);
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {

        }
    }
}
