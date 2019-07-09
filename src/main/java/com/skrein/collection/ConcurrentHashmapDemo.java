package com.skrein.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :hujiansong
 * @date :2019/6/26 10:26
 * @since :1.8
 */
public class ConcurrentHashmapDemo {
    public static void main(String[] args) {
        Map<String,Object> objectMap = new ConcurrentHashMap<>(8);
        objectMap.put("123","123");
    }
}
