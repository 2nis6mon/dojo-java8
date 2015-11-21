package org.dojo.java8.exercise3;

import org.dojo.java8.model.Person;
import org.dojo.java8.model.PersonAgeStatistic;
import org.dojo.java8.model.PersonLibrary;

import java.util.*;
import java.util.stream.Collectors;

public class StreamOperations {

    private static final String UNKNOWN_LANGUAGE = "Unknown";
    private List<Person> persons = PersonLibrary.getPersons("/person_data.json");

    //TODO Convert users List to stream and use filter and count
    public long countPersonsWithAge(int age) {
        return persons.stream()
                .filter(person -> person.getAge() == age)
                .count();
    }

    //TODO Convert users List to stream and use filter static method and count
    public long countMarriedPeople() {
        return persons.stream()
                .filter(Person::isMarried)
                .count();
    }

    // TODO Use double filter and findFirst
    // TODO to finish you need Optional.map and Optional.orElse
    public String retrieveLanguage(String name, String surname) {
        return persons.stream()
                .filter(person -> name.equals(person.getName()))
                .filter(person -> surname.equals(person.getSurname()))
                .findFirst()
                .map(Person::getLanguage)
                .orElse(UNKNOWN_LANGUAGE);
    }

    //TODO Use sorted and replace specific comparator with Comparator.comparing and thenComparing
    //TODO with static methods. Use limit method and collect with Collectors to generate new List
    public List<Person> firstTenPersons() {
        return persons.stream()
                .sorted(Comparator.comparing(Person::getSurname)
                        .thenComparing(Person::getName))
                .limit(10)
                .collect(Collectors.toList());
    }

    // TODO Use filter and Collectors.groupingBy
    public Map<Integer, List<Person>> retrieveMarriedPeopleByAge() {
        return persons.stream()
                .filter(Person::isMarried)
                .collect(Collectors.groupingBy(Person::getAge));
    }

    // TODO Use Collector.summarizingInt
    // TODO and use the result to make the new PersonAgeStatistic
    public PersonAgeStatistic generateAgeStatistic() {
        IntSummaryStatistics summarizingInt = persons.stream().collect(Collectors.summarizingInt(Person::getAge));
        return new PersonAgeStatistic(summarizingInt.getCount(), summarizingInt.getMin(), summarizingInt.getMax(), summarizingInt.getAverage());
    }

}
