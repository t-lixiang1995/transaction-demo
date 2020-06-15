package com.kljs.rabbitmq.order.util;

import java.time.*;
import java.util.Date;

public class DateTimeUtil {
    private static ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.now());

    public static LocalDateTime toLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp * 1000), ZoneId.systemDefault());
    }

    public static long toTimestamp(LocalDateTime dateTime) {
        return dateTime.toInstant(offset).toEpochMilli() / 1000;
    }

    public static LocalDateTime beginOfDay(LocalDateTime dateTime) {
        return dateTime.withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime endOfDay(LocalDateTime dateTime) {
        return dateTime.withHour(23).withMinute(59).withSecond(59);
    }

    public static Date toDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    public static int expireTimeout(LocalDateTime curDateTime) {
        LocalDateTime todayEnd = curDateTime.plusHours(2).withMinute(0).withSecond(0);
        long timeBreak = DateTimeUtil.toTimestamp(todayEnd) - DateTimeUtil.toTimestamp(curDateTime);
        int timeout = new Double(timeBreak).intValue();
        return timeout;
    }

    public static int expireTimeout(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        long timeBreak = DateTimeUtil.toTimestamp(endDateTime) - DateTimeUtil.toTimestamp(startDateTime);
        int timeout = new Double(timeBreak).intValue();
        return timeout;
    }
}
