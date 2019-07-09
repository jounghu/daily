package com.skrein.java8dateapi;


import java.time.*;
import java.time.format.DateTimeFormatter;


/**
 * @author :hujiansong
 * @date :2019/6/24 16:21
 * @since :1.8
 */
public class NewApiTimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static long dateToLong(LocalDateTime dateTime) {
        return LocalDateTime.from(dateTime).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    /**
     * 获取指定字符串当天零点的时间戳
     *
     * @param dateText yyyy-MM-dd
     */
    public static long ofDateZero(String dateText) {
        LocalDate parse = LocalDate.parse(dateText, FORMATTER);
        LocalDateTime of = LocalDateTime.of(parse, LocalTime.of(0, 0));
        return dateToLong(of);
    }


    /**
     * 获取指定字符串当天零点的时间戳
     */
    public static long ofTodayStart() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withHour(0)
                .withMinute(0)
                .withSecond(0);
        return dateToLong(localDateTime);
    }

    /**
     * 获取指定字符串当天零点的时间戳
     */
    public static long ofTodayEnd() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withHour(23)
                .withMinute(59)
                .withSecond(59);
        return dateToLong(localDateTime);
    }

    /**
     * 获取当前小时 时间戳
     *
     * @return
     */
    public static long ofCurrentHour() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.withMinute(0)
                .withSecond(0);
        return dateToLong(localDateTime);
    }

    /**
     * 获取前三天时间戳
     *
     * @return
     */
    public static long ofOld3Days() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusDays(3)
                .withHour(0)
                .withMinute(0)
                .withSecond(0);
        return dateToLong(now);
    }

    /**
     * 获取前一个小时时间戳
     *
     * @return
     */
    public static long ofPreHour() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(1)
                .withMinute(0)
                .withSecond(0);
        return dateToLong(now);
    }

    /**
     * 获取当天9点时间戳
     */
    public static long ofNineHour() {
        LocalDateTime now = LocalDateTime.now();
        now = now.withHour(9)
                .withMinute(0)
                .withSecond(0);
        return dateToLong(now);
    }

    /**
     * 格式化时间戳，返回yyyy-MM-dd HH:mm:ss时间格式
     *
     * @param timestamp 秒
     * @return yyyy-MM-dd HH:mm:ss时间格式
     */
    public static String fmt(long timestamp) {
        DateTimeFormatter fmat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fmat.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
    }


    public static void main(String[] args) {
        System.out.println(fmt(1561368758000L));
    }
}
