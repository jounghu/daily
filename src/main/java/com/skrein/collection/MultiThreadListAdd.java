package com.skrein.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @author :hujiansong
 * @date :2019/6/25 16:56
 * @since :1.8
 */
public class MultiThreadListAdd {

    private static class ThreadA extends Thread {

        List<Integer> list;

        public ThreadA(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2000000; i++) {
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> i = new Vector<>();
        ThreadA a = new ThreadA(i);
        ThreadA b= new ThreadA(i);
        a.start();
        b.start();


        a.join();
        b.join();
        System.out.println(i.size());


    }

}
