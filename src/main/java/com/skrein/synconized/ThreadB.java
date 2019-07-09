package com.skrein.synconized;

/**
 * @author :hujiansong
 * @date :2019/6/25 10:35
 * @since :1.8
 */
public class ThreadB extends Thread {

    private SynconizeObj synconizeObj;

    public ThreadB(SynconizeObj synconizeObj) {
        this.synconizeObj = synconizeObj;
    }

    @Override
    public void run() {
        synconizeObj.addB("b");
    }
}
