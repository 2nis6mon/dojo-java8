package org.dojo.java8.exercise3;

import org.assertj.core.api.Condition;
import org.dojo.java8.model.Person;
import org.dojo.java8.model.PersonAgeStatistic;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamOperationsTest {

    StreamOperations streamOperations = new StreamOperations();


    @Test
    public void check_count_persons_with_age() {
        assertThat(streamOperations.countPersonsWithAge(34)).isEqualTo(12L);
        assertThat(streamOperations.countPersonsWithAge(55)).isEqualTo(5L);
        assertThat(streamOperations.countPersonsWithAge(64)).isEqualTo(11L);
        assertThat(streamOperations.countPersonsWithAge(110)).isEqualTo(0L);
    }

    @Test
    public void check_count_married_people() {
        assertThat(streamOperations.countMarriedPeople()).isEqualTo(493L);
    }

    @Test
    public void check_retrieve_language_by_name_and_surname() {
        assertThat(streamOperations.retrieveLanguage("Johnny", "Andrews")).isEqualTo("Unknown");
        assertThat(streamOperations.retrieveLanguage("Julia", "Gray")).isEqualTo("Italian");
        assertThat(streamOperations.retrieveLanguage("Lucas", "Santana")).isEqualTo("Unknown");
    }


    @Test
    public void check_first_ten_persons() {
        // when
        List<Person> firstTen = streamOperations.firstTenPersons();

        // then
        assertThat(firstTen.toString()).isEqualTo("[{\"name\":\"Donald\",\"surname\":\"Adams\",\"age\":84,\"language\":\"Oriya\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Nicholas\",\"surname\":\"Adams\",\"age\":28,\"language\":\"Telugu\",\"married\":false,\"children\":null}, " +
                "{\"name\":\"Ruby\",\"surname\":\"Adams\",\"age\":4,\"language\":\"Somali\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Scott\",\"surname\":\"Adams\",\"age\":70,\"language\":\"Latvian\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Willie\",\"surname\":\"Adams\",\"age\":6,\"language\":\"Catalan\",\"married\":false,\"children\":null}, " +
                "{\"name\":\"Anna\",\"surname\":\"Alexander\",\"age\":19,\"language\":\"Georgian\",\"married\":false,\"children\":null}, " +
                "{\"name\":\"Donald\",\"surname\":\"Alexander\",\"age\":48,\"language\":\"Chinese\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Lori\",\"surname\":\"Alexander\",\"age\":87,\"language\":\"Moldovan\",\"married\":false,\"children\":null}, " +
                "{\"name\":\"Mildred\",\"surname\":\"Alexander\",\"age\":45,\"language\":\"Macedonian\",\"married\":false,\"children\":null}, " +
                "{\"name\":\"Ruth\",\"surname\":\"Alexander\",\"age\":95,\"language\":\"Armenian\",\"married\":true,\"children\":null}]");
    }


    @Test
    public void check_retrieveMarriedPeopleByAge() {
        // when
        Map<Integer, List<Person>> result = streamOperations.retrieveMarriedPeopleByAge();

        // then
        assertThat(result.get(48)).hasSize(6);


        
        
        assertThat(result.get(48).toString()).isEqualTo("[{\"name\":\"Carlos\",\"surname\":\"Holmes\",\"age\":48,\"language\":\"Kurdish\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Jimmy\",\"surname\":\"Sims\",\"age\":48,\"language\":\"Japanese\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Kathryn\",\"surname\":\"Hernandez\",\"age\":48,\"language\":\"Guaraní\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Keith\",\"surname\":\"Hart\",\"age\":48,\"language\":\"German\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Donald\",\"surname\":\"Alexander\",\"age\":48,\"language\":\"Chinese\",\"married\":true,\"children\":null}, " +
                "{\"name\":\"Jason\",\"surname\":\"Frazier\",\"age\":48,\"language\":\"Haitian Creole\",\"married\":true,\"children\":null}]");

        for (Integer key : result.keySet()) {
            assertThat(result.get(key)).doNotHave(personsNotMarried()).doNotHave(personsWithoutSameAge(key));
        }
    }

    private Condition<? super Person> personsNotMarried() {
        return new Condition<Person>() {
            public boolean matches(Person person) {
                return !person.isMarried();
            }
        };

    }

    @Test
    public void should_return_personAgestatistics() {
        assertThat(streamOperations.generateAgeStatistic()).isEqualTo(new PersonAgeStatistic(1000, 1, 100, 51.2));
    }

    private Condition<? super Person> personsWithoutSameAge(int age) {
        return new Condition<Person>() {
            public boolean matches(Person person) {
                return age != person.getAge();
            }
        };

    }

}