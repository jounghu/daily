package com.skrein.synconized;

/**
 * @author :hujiansong
 * @date :2019/6/25 10:35
 * @since :1.8
 */
public class ThreadA extends Thread {

    private SynconizeObj synconizeObj;

    public ThreadA(SynconizeObj synconizeObj) {
        this.synconizeObj = synconizeObj;
    }

    @Override
    public void run() {
        synconizeObj.addI("a");
    }
}
