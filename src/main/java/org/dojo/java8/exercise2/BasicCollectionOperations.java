package org.dojo.java8.exercise2;

import org.dojo.java8.model.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCollectionOperations {

    private static Map<String, BigDecimal> CURRENCIES_BY_ISOCODE = new HashMap<>();

    private static Map<Integer, Long> FIBONACCI_CACHE = new HashMap<>();

    static {
        CURRENCIES_BY_ISOCODE.put("USD", BigDecimal.valueOf(0.91));
        CURRENCIES_BY_ISOCODE.put("GPB", BigDecimal.valueOf(1.38));
        CURRENCIES_BY_ISOCODE.put("AUD", BigDecimal.valueOf(0.70));
        CURRENCIES_BY_ISOCODE.put("EUR", BigDecimal.valueOf(1));

        FIBONACCI_CACHE.put(0, 0L);
        FIBONACCI_CACHE.put(1, 1L);
        FIBONACCI_CACHE.put(2, 1L);
    }

    //TODO Refactor with Iterable forEach method
    public static void resetAge(List<Person> persons) {
        persons.forEach(person -> person.setAge(-1));
    }

    //TODO Refactor with Collection removeIf method and use method reference
    public static void removeMarriedPeople(List<Person> persons) {
        persons.removeIf(Person::isMarried);
    }

    //TODO Refactor with List replaceAll method
    public static void addOneDayToDates(List<LocalDate> localDates) {
        localDates.replaceAll(localDate -> localDate.plusDays(1));
    }

    //TODO Use Map getOrDefault method
    public static BigDecimal exchangeRateWithEuro(String isoCode) {
        return CURRENCIES_BY_ISOCODE.getOrDefault(isoCode, BigDecimal.ONE);
    }

    //TODO Use Map computeIfAbsent method
    private static long fibonacciComputation(int number) {
        return FIBONACCI_CACHE.computeIfAbsent(number, i -> fibonacciComputation(i - 1) + fibonacciComputation(i - 2));
    }

    public static List<Long> fibonacci(int expectedNumberResult) {
        List<Long> result = new ArrayList<>(expectedNumberResult);

        for (int i = 1; i <= expectedNumberResult; i++) {

            result.add(fibonacciComputation(i));
        }
        return result;
    }
}
