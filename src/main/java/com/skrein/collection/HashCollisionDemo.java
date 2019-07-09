package com.skrein.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :hujiansong
 * @date :2019/6/25 17:54
 * @since :1.8
 */
public class HashCollisionDemo {
    public static void main(String[] args) {


        List<Integer> sameHashCode = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if ((i & 3) == 0) {
                sameHashCode.add(i);
            }
        }

        sameHashCode.forEach(i -> {
            System.out.println(i + "=====" + ((i & 4) == 0));
        });


    }
}
