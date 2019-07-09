package com.skrein.synconized;

/**
 * @author :hujiansong
 * @date :2019/6/25 10:35
 * @since :1.8
 */
public class SynconizeObj {

    private int num;

    private static int staticNum;

    public synchronized void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num=" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static synchronized void addB(String username) {
        try {
            if (username.equals("a")) {
                staticNum = 200;
                System.out.println("static a set over!");
                Thread.sleep(2000);
            } else {
                staticNum = 300;
                System.out.println("static b set over!");
            }
            System.out.println(username + " num=" + staticNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
