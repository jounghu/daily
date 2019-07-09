package com.skrein.java8dateapi;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author :hujiansong
 * @date :2019/6/24 17:59
 * @since :1.8
 */
public class NewDateTimeAPI {

    private static long getTimestamp(String dateText) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 解析成java8 日期
        LocalDate parse = LocalDate.parse(dateText, pattern);
        // 解析成java8 日期时间(只有日期时间才有时间戳)
        LocalDateTime dateTime = LocalDateTime.of(parse, LocalTime.of(0, 0));
        // 转换成时间戳
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    private static long getLastMonthTimestamp(String dateText) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 解析成java8 日期
        LocalDate parse = LocalDate.parse(dateText, pattern);
        // 解析成java8 日期时间(只有日期时间才有时间戳)
        LocalDateTime dateTime = LocalDateTime.of(parse, LocalTime.of(0, 0));


        // 转换成时间戳
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    private static long specialDate(String dateText) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 解析成java8 日期
        LocalDate parse = LocalDate.parse(dateText, pattern);
        // 解析成java8 日期时间(只有日期时间才有时间戳)
        LocalDateTime dateTime = LocalDateTime.of(parse, LocalTime.of(0, 0));

        // 获取当前日期的12点0分0秒的时间戳
        dateTime = dateTime.withHour(12)
                .withMinute(0)
                .withSecond(0);

        // 转换成时间戳
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public static String ts2Str(long second) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(pattern);
    }




    public static void main(String[] args) {
//        System.out.println(ts2Str(System.currentTimeMillis() / 1000L));
//        LocalDateTime.ofInstant(Instant.ofEpochSecond());

    }
}
