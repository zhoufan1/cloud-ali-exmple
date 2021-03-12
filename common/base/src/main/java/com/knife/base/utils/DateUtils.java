package com.knife.base.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    private DateUtils() {
    }

    /**
     * 给定 format 将 Date 转换成 string
     **/
    public static String format(Date date, DataFormatEnum format) {
        return null == date ? format.realVal() : new SimpleDateFormat(format.realVal()).format(date);
    }

    /**
     * 给定 format 将 LocalDate 转换成 string
     **/
    public static String format(LocalDate date, DataFormatEnum format) {
        return null == date ? format.realVal() : date.format(DateTimeFormatter.ofPattern(format.realVal()));
    }

    /**
     * 给定 format 将 LocalDateTime 转换成 string
     **/
    public static String format(LocalDateTime date, DataFormatEnum format) {
        return null == date ? format.realVal() : date.format(DateTimeFormatter.ofPattern(format.realVal()));
    }

    /**
     * 给定 format 将秒时间戳转换成 string
     **/
    public static String format(long unixTime, DataFormatEnum format) {
        return format(ofLocalDateTime(unixTime), format);
    }

    /**
     * 给定 format 将 string 转换成 Date
     **/
    public static Date ofDate(String sDate, DataFormatEnum format) {
        try {
            return new SimpleDateFormat(format.realVal()).parse(sDate);
        } catch (Exception e) {
            throw new RuntimeException("ofDate error, sDate: " + sDate + " format: " + format.realVal(), e);
        }
    }

    /**
     * 将秒时间戳 转换成 Date
     **/
    public static Date ofDate(long time) {
        return Date.from(Instant.ofEpochMilli(time));
    }

    /**
     * 给定 format 将 string 转换成 LocalDate
     **/
    public static LocalDate ofLocalDate(String sDate, DataFormatEnum format) {
        return LocalDate.parse(sDate, DateTimeFormatter.ofPattern(format.realVal()));
    }

    /**
     * 将毫秒时间戳 转换成 LocalDate
     **/
    public static LocalDate date2Local(long date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将秒时间戳 转换成 LocalDate
     **/
    public static LocalDate ofLocalDate(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将毫秒时间戳 转换成 LocalDateTime
     **/
    public static LocalDateTime time2Local(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    /**
     * 给定 format 将 string 转换成 LocalDateTime
     **/
    public static LocalDateTime ofLocalDateTime(String sDate, DataFormatEnum format) {
        return LocalDateTime.parse(sDate, DateTimeFormatter.ofPattern(format.realVal()));
    }

    /**
     * 将秒时间戳 转换成 LocalDateTime
     **/
    public static LocalDateTime ofLocalDateTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    /**
     * 当前系统时间 Date 类型
     **/
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 给定 format 将当前系统时间转换成 string
     **/
    public static String now(DataFormatEnum format) {
        return format(now(), format);
    }

    /**
     * 当前系统时间秒
     **/
    public static Long second() {
        return Instant.now().getEpochSecond();
    }

    /**
     * LocalDate 时间秒
     **/
    public static Long second(LocalDate date) {
        return null == date ? 0L : date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     *  LocalDateTime 时间秒
     **/
    public static Long second(LocalDateTime dateTime) {
        return null == dateTime ? 0L : dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 当前系统时间毫秒
     **/
    public static Long time() {
        return System.currentTimeMillis();
    }

    /**
     * 将 LocalDate 转换成毫秒
     **/
    public static Long time(LocalDate date) {
        return null == date ? 0L : date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将 LocalDateTime 转换成毫秒
     **/
    public static Long time(LocalDateTime dateTime) {
        return null == dateTime ? 0L : dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 给定秒时间戳得出当天的开始时间秒
     **/
    public static long ofDayStart(long unixTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ofDate(unixTime));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.toInstant().getEpochSecond();
    }

    /**
     * 在给定的 Date 时间加 days 天
     **/
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * from 1 (Monday) to 7 (Sunday)
     **/
    public static int dayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    public static String subMailTime(long dateTime) {
        LocalDateTime time = time2Local(dateTime);
        return time.getMonth().getValue() + "月" + time.getDayOfMonth() + "日"
                + " " + time.getHour() + ":" + time.getMinute();
    }
}
