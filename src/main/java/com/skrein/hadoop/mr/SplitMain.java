package com.skrein.hadoop.mr;

/**
 * @author :hujiansong
 * @date :2019/7/8 16:31
 * @since :1.8
 */
public class SplitMain {
    public static void main(String[] args) {
        String str = "29\t15001736467\t192.168.100.27\t\t76 \t74 \t404";
        String[] s = str.split("\t");
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
