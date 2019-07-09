package com.skrein.java8dateapi;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: hujiansong
 * @since: 2019/1/23 16:02
 */
public class TimeStampUtils {

    public static long ONE_DAY_SECONDS = 24 * 60 * 60;
    public static long ONE_HOUR_SECONDS = 60 * 60;
    public static long SIXTEEN_HOUR_SECONDS = 16 * 60 * 60;

    private static ThreadLocal<SimpleDateFormat> DATE_SDF = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private static ThreadLocal<SimpleDateFormat> DATE_TIME_SDF = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    public static long earlyTimeStamp(String date) throws ParseException {
        date = date.concat(" 00:00:00");
        return DATE_TIME_SDF.get().parse(date).getTime() / 1000;
    }

    public static long endlyTimeStamp(String date) throws ParseException {
        date = date.concat(" 23:59:59");
        return DATE_TIME_SDF.get().parse(date).getTime() / 1000;
    }

    public static long currentDayTimestamp() {
        Calendar calendar = initCalendar();
        return calendar.getTime().getTime() / 1000;
    }

    private static Calendar initCalendar() {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    public static long getOlder3DayTimestamp() {
        Calendar calendar = initCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        return calendar.getTime().getTime() / 1000;
    }

    /**
     * 获取当前小时时间戳
     *
     * @return
     */
    public static long getCurrentHourTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        return (currentTimeMillis - currentTimeMillis % 3600000) / 1000;
    }

    /**
     * 获取前一个小时时间戳到秒
     *
     * @return
     */
    public static long getLastHourTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        long currentHour = currentTimeMillis - currentTimeMillis % 3600000;
        long oneHour = 60 * 60 * 1000;
        return (currentHour - oneHour) / 1000;
    }

    /**
     * 格式化时间戳，返回yyyy-MM-dd HH:mm:ss时间格式
     *
     * @param timestamp
     * @return yyyy-MM-dd HH:mm:ss时间格式
     */
    public static String formatTimestamp(long timestamp) {
        return DATE_TIME_SDF.get().format(new Date(timestamp));
    }

    /***
     * 获取当天零点时间戳
     * @return
     */
    public static long getZeroTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero.getTime() / 1000;
    }

    /***
     * 获取当天零点时间戳
     * @return
     */
    public static long getYesterdayZero() {
        return getZeroTimestamp() - ONE_DAY_SECONDS;
    }

    /***
     * 获取当天九时间戳
     * @return
     */
    public static long getNineTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero.getTime() / 1000;
    }

    public static void main(String[] args) {

        System.out.println(TimeStampUtils.currentDayTimestamp());
    }


}
