package com.skrein.trietree;

import java.util.TreeMap;

/**
 * @author :hujiansong
 * @date :2019/6/25 15:22
 * @since :1.8
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1,"123");
        treeMap.put(3,"123");
        treeMap.put(4,"123");

        System.out.println(treeMap.floorKey(2));
    }
}
