package org.dojo.java8.exercise3;

import org.dojo.java8.model.Person;
import org.dojo.java8.model.PersonAgeStatistic;
import org.dojo.java8.model.PersonLibrary;

import java.util.*;

public class StreamOperations {

    private static final String UNKNOWN_LANGUAGE = "Unknown";
    private List<Person> persons = PersonLibrary.getPersons("/person_data.json");

    //TODO Convert users List to stream and use filter and count
    public long countPersonsWithAge(int age) {
        long count = 0;

        for (Person person : persons) {
            if (person.getAge() == age) {
                count++;
            }
        }

        return count;
    }

    //TODO Convert users List to stream and use filter static method and count
    public long countMarriedPeople() {
        long count = 0;

        for (Person person : persons) {
            if (person.isMarried()) {
                count++;
            }
        }

        return count;
    }

    // TODO Use double filter and findFirst
    // TODO to finish you need Optional.map and Optional.orElse
    public String retrieveLanguage(String name, String surname) {
        String language = UNKNOWN_LANGUAGE;

        for (Person person : persons) {
            if (name.equals(person.getName()) && surname.equals(person.getSurname())) {
                if (person.getLanguage() != null) {
                    return person.getLanguage();
                }
                break;
            }
        }

        return language;
    }

    //TODO Use sorted and replace specific comparator with Comparator.comparing and thenComparing
    //TODO with static methods. Use limit method and collect with Collectors to generate new List
    public List<Person> firstTenPersons() {
        List<Person> personOrdered = new ArrayList<>(persons.size());
        personOrdered.addAll(persons);

        Collections.sort(personOrdered, new PersonComparator());

        return personOrdered.subList(0, 10);
    }

    // TODO Use filter and Collectors.groupingBy
    public Map<Integer, List<Person>> retrieveMarriedPeopleByAge() {
        Map<Integer, List<Person>> marriedPeopleByAge = new HashMap<>();
        List<Person> personsByAge;
        for (Person person : persons) {
            if (person.isMarried()) {
                personsByAge = marriedPeopleByAge.get(person.getAge());
                if (personsByAge == null) {
                    personsByAge = new ArrayList<>();
                    marriedPeopleByAge.put(person.getAge(), personsByAge);
                }
                personsByAge.add(person);
            }
        }

        return marriedPeopleByAge;
    }

    // TODO Use Collector.summarizingInt
    // TODO and use the result to make the new PersonAgeStatistic
    public PersonAgeStatistic generateAgeStatistic() {
        long count = 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        int sum = 0;

        for (Person person : persons) {
            int age = person.getAge();
            if (age > max) {
                max = age;
            }
            if (age < min) {
                min = age;
            }
            count++;
            sum += age;
        }
        double average = (double) sum / count;

        return new PersonAgeStatistic(count, min, max, average);
    }

    private class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            int surnameComparaison = p1.getSurname().compareTo(p2.getSurname());
            if (surnameComparaison == 0) {
                return p1.getName().compareTo(p2.getName());
            } else {
                return surnameComparaison;
            }
        }
    }

}
