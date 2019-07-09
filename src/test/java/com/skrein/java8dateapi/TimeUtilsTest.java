package com.skrein.java8dateapi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author :hujiansong
 * @date :2019/6/24 17:44
 * @since :1.8
 */
public class TimeUtilsTest {

    @Test
    public void ofDateZero() {

    }

    @Test
    public void ofTodayStart() {
        assertEquals(TimeUtils.ofTodayStart(),TimeStampUtils.getZeroTimestamp());
    }

    @Test
    public void ofPreDayStart() {
        assertEquals(TimeUtils.ofPreDayStart(),TimeStampUtils.getYesterdayZero());
    }

    @Test
    public void ofTodayEnd() {

    }

    @Test
    public void ofCurrentHour() {
        assertEquals(TimeUtils.ofCurrentHour(),TimeStampUtils.getCurrentHourTimestamp());
    }

    @Test
    public void ofOld3Days() {
        assertEquals(TimeUtils.ofOld3Days(),TimeStampUtils.getOlder3DayTimestamp());
    }

    @Test
    public void ofPreHour() {
        assertEquals(TimeUtils.ofPreHour(),TimeStampUtils.getLastHourTimestamp());
    }

    @Test
    public void ofNineHour() {
        assertEquals(TimeUtils.ofNineHour(),TimeStampUtils.getNineTimestamp());
    }

    @Test
    public void fmt() {
        assertEquals(TimeUtils.fmt(1561369654),TimeStampUtils.formatTimestamp(1561369654000L));
    }
}