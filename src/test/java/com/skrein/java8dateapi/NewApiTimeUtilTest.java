package com.skrein.java8dateapi;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * @author :hujiansong
 * @date :2019/6/24 17:17
 * @since :1.8
 */
public class NewApiTimeUtilTest {

    @Test
    public void ofDateZero() throws ParseException {
        String dateText = "2018-09-11";
        assertEquals(NewApiTimeUtil.ofDateZero(dateText),TimeStampUtils.earlyTimeStamp(dateText));
    }

    @Test
    public void ofTodayStart() {
        assertEquals(NewApiTimeUtil.ofTodayStart(),TimeStampUtils.getZeroTimestamp());
    }

    @Test
    public void ofTodayEnd() {

    }

    @Test
    public void ofCurrentHour() {
        assertEquals(NewApiTimeUtil.ofCurrentHour(),TimeStampUtils.getCurrentHourTimestamp());
    }

    @Test
    public void ofOld3Days() {
        assertEquals(NewApiTimeUtil.ofOld3Days(),TimeStampUtils.getOlder3DayTimestamp());

    }

    @Test
    public void ofPreHour() {
        assertEquals(NewApiTimeUtil.ofPreHour(),TimeStampUtils.getLastHourTimestamp());

    }

    @Test
    public void ofNineHour() {
        assertEquals(NewApiTimeUtil.ofNineHour(),TimeStampUtils.getNineTimestamp());
    }

    @Test
    public void main() {
    }
}