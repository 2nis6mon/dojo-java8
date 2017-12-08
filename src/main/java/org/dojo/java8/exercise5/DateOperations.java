package org.dojo.java8.exercise5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateOperations {

    /**
     * Parse String date without times
     *
     * @param date format  dd/MM/yyyy
     * @return
     */
    //TODO Replace with LocalDate and DateTimeFormatter
    //TODO CHANGE DE RETURN TYPE TO LocalDate
    public static Date parseDate(String date) {
        try {  //SimpleDateFormat not thread safe must create new formater for each request
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("bad format " + date);
        }
    }

    /**
     * parse String date with time
     *
     * @param date format  dd/MM/yyyy HH:mm:ss
     * @return
     */
    //TODO Replace with LocalDateTime and DateTimeFormatter
    //TODO CHANGE DE RETURN TYPE TO LocalDateTime
    public static Date parseDateTime(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return format.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("bad format " + date);
        }
    }

    //TODO Replace with LocalDate and use Period
    public static int age(Date birthday, Date now) {
        Calendar calBirthday = Calendar.getInstance();
        calBirthday.setTime(birthday);

        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);

        int age = calNow.get(Calendar.YEAR) - calBirthday.get(Calendar.YEAR);
        if (calNow.get(Calendar.DAY_OF_YEAR) < calBirthday.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

    //TODO Replace dayDate by LocalDate and result by LocalDateTime
    //TODO CHANGE DE RETURN TYPE TO LocalDateTime
    public static Date dayDateWithTime(Date dayDate, int hour, int minute, int second) {
        Calendar calendarDayDate = Calendar.getInstance();
        calendarDayDate.setTime(dayDate);

        calendarDayDate.set(Calendar.HOUR, hour);
        calendarDayDate.set(Calendar.MINUTE, minute);
        calendarDayDate.set(Calendar.SECOND, second);

        return calendarDayDate.getTime();
    }

    //TODO Replace By LocalDateTime
    //TODO CHANGE DE RETURN TYPE TO LocalDateTime
    public static Date addDuration(Date date, int minute) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);

        calendarDate.add(Calendar.MINUTE, minute);

        return calendarDate.getTime();
    }

    //TODO Replace by LocalDateTime
    public static boolean dayAreEquals(Date firstDateWithTime, Date secondDateWithTime) {
        Calendar calendarDay1 = Calendar.getInstance();
        calendarDay1.setTime(firstDateWithTime);

        Calendar calendarDay2 = Calendar.getInstance();
        calendarDay2.setTime(secondDateWithTime);

        return calendarDay1.get(Calendar.YEAR) == calendarDay2.get(Calendar.YEAR) &&
                calendarDay1.get(Calendar.DAY_OF_YEAR) == calendarDay2.get(Calendar.DAY_OF_YEAR);
    }

    //TODO parse with LocalDateTime and use ZonedDateTime for conversion
    public static String convertToTimeZone(String dateWithTime, ZoneId timeZoneFrom, ZoneId timeZoneTo) {
        try {
            SimpleDateFormat parserFrom = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            parserFrom.setTimeZone(TimeZone.getTimeZone(timeZoneFrom));

            Date date = parserFrom.parse(dateWithTime);

            SimpleDateFormat formatWithTimeZone = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            formatWithTimeZone.setTimeZone(TimeZone.getTimeZone(timeZoneTo));

            return formatWithTimeZone.format(date);

        } catch (ParseException e) {
            throw new IllegalArgumentException("bad format " + dateWithTime);
        }
    }
}
