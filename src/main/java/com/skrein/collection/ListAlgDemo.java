package com.skrein.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :hujiansong
 * @date :2019/6/25 16:20
 * @since :1.8
 */
public class ListAlgDemo {
    public static void main(String[] args) {
        List<Integer> lists = new LinkedList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.remove(1);
    }
}
