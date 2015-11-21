package org.dojo.java8.exercise5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateOperations {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    //TODO Replace with LocalDate and DateTimeFormatter
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    //TODO Replace with LocalDateTime and DateTimeFormatter
    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    //TODO Replace with LocalDate and use Period
    public static int age(LocalDate birthday, LocalDate now) {
        return Period.between(birthday, now).getYears();
    }

    //TODO Replace dayDate by LocalDate and result by LocalDateTime
    public static LocalDateTime dayDateWithTime(LocalDate dayDate, int hour, int minute, int second) {
       return dayDate.atTime(hour, minute, second);
    }

    //TODO Replace By LocalDateTime
    public static LocalDateTime addDuration(LocalDateTime date, int minute) {
        return date.plusMinutes(minute);
    }

    //TODO Replace by LocalDateTime
    public static boolean dayAreEquals(LocalDateTime firstDateWithTime, LocalDateTime secondDateWithTime) {
        return firstDateWithTime.toLocalDate().equals(secondDateWithTime.toLocalDate());
    }

    //TODO parse with LocalDateTime and use ZonedDateTime for conversion
    public static String convertToTimeZone(String dateWithTime, ZoneId timeZoneFrom, ZoneId timeZoneTo) {
        return LocalDateTime
                .parse(dateWithTime, DATE_TIME_FORMATTER)
                .atZone(timeZoneFrom)
                .withZoneSameInstant(timeZoneTo)
                .format(DATE_TIME_FORMATTER);
    }
}
