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

        for (Person person : persons) {
            person.setAge(-1);
        }

    }

    //TODO Refactor with Collection removeIf method and use method reference
    public static void removeMarriedPeople(List<Person> persons) {
        List<Person> personsToRemove = new ArrayList<>();

        for (Person person : persons) {
            if (person.isMarried()) {
                personsToRemove.add(person);
            }
        }

        persons.removeAll(personsToRemove);
    }

    //TODO Refactor with List replaceAll method
    public static void addOneDayToDates(List<LocalDate> localDates) {

        for (int i = 0; i < localDates.size(); i++) {
            LocalDate localDate = localDates.get(i);
            LocalDate newDate = localDate.plusDays(1);

            localDates.set(i, newDate);
        }
    }

    //TODO Use Map getOrDefault method
    public static BigDecimal exchangeRateWithEuro(String isoCode) {
        BigDecimal currencyByIsocode = CURRENCIES_BY_ISOCODE.get(isoCode);
        if (currencyByIsocode != null) {
            return currencyByIsocode;
        }

        return BigDecimal.ONE; //Default Currency is One (no conversion)
    }

    //TODO Use Map computeIfAbsent method
    private static long fibonacciComputation(int number) {
        if (FIBONACCI_CACHE.containsKey(number)) {
            return FIBONACCI_CACHE.get(number);
        }

        long fibonacci = fibonacciComputation(number - 1) + fibonacciComputation(number - 2);

        FIBONACCI_CACHE.put(number, fibonacci);

        return fibonacci;
    }

    public static List<Long> fibonacci(int expectedNumberResult) {
        List<Long> result = new ArrayList<>(expectedNumberResult);

        for (int i = 1; i <= expectedNumberResult; i++) {

            result.add(fibonacciComputation(i));
        }
        return result;
    }
}
