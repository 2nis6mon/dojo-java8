package org.dojo.java8.exercise2;

import org.assertj.core.api.Condition;
import org.dojo.java8.model.Person;
import org.dojo.java8.model.PersonLibrary;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicCollectionOperationsTest {

    List<Person> persons = PersonLibrary.getPersons("/person_data.json");

    @Test
    public void should_reset_ages() {
        //when
        BasicCollectionOperations.resetAge(persons);

        //then
        assertThat(persons).doNotHave(personWithAge());

    }

    @Test
    public void should_remove_married_people() {
        //when
        BasicCollectionOperations.removeMarriedPeople(persons);

        //then
        assertThat(persons).hasSize(507);
        assertThat(persons).doNotHave(personMarried());
    }

    @Test
    public void should_replace_all_date_by_date_plus_one_day() {
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(LocalDate.of(2014, 2, 10));
        localDates.add(LocalDate.of(2014, 2, 15));
        localDates.add(LocalDate.of(2014, 2, 18));


        BasicCollectionOperations.addOneDayToDates(localDates);

        assertThat(localDates).containsExactly(LocalDate.of(2014, 2, 11), LocalDate.of(2014, 2, 16), LocalDate.of(2014, 2, 19));

    }

    @Test
    public void should_return_exchange_rate() {
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("EUR")).isEqualByComparingTo("1");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("GPB")).isEqualByComparingTo("1.38");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("USD")).isEqualByComparingTo("0.91");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("AUD")).isEqualByComparingTo("0.70");
        assertThat(BasicCollectionOperations.exchangeRateWithEuro("AZERTY")).isEqualByComparingTo("1");
    }

    @Test
    public void should_return_45_fibonacci_number() {
        assertThat(BasicCollectionOperations.fibonacci(45)).containsExactly(1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L, 46368L, 75025L, 121393L, 196418L, 317811L, 514229L, 832040L, 1346269L, 2178309L, 3524578L, 5702887L, 9227465L, 14930352L, 24157817L, 39088169L, 63245986L, 102334155L, 165580141L, 267914296L, 433494437L, 701408733L, 1134903170L);
    }

    private Condition<Person> personWithAge() {
        return new Condition<Person>() {
            public boolean matches(Person person) {
                return person.getAge() != -1;
            }
        };
    }

    private Condition<Person> personMarried() {
        return new Condition<Person>() {
            public boolean matches(Person person) {
                return person.isMarried();
            }
        };
    }
}
