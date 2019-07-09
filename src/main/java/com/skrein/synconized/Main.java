package com.skrein.synconized;

/**
 * @author :hujiansong
 * @date :2019/6/25 10:37
 * @since :1.8
 */
public class Main {
    public static void main(String[] args) {
        SynconizeObj synconizeObj = new SynconizeObj();

        ThreadA threadA = new ThreadA(synconizeObj);
        threadA.start();

        ThreadB threadB = new ThreadB(synconizeObj);
        threadB.start();
    }
}
