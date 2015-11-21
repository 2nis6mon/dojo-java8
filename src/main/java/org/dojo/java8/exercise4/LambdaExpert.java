package org.dojo.java8.exercise4;

import org.dojo.java8.model.Child;
import org.dojo.java8.model.Person;
import org.dojo.java8.model.PersonLibrary;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaExpert {

	private List<Person> persons = PersonLibrary.getPersons("/person_data_with_children.json");

	// TODO use map, flatMap, sorted and limit
	public List<Child> firstTwoYoungerChildsByName() {
		return persons.stream()
				.map(Person::getChildren)
				.flatMap(Collection::stream)
				.sorted(Comparator.comparing(Child::getName)
						.thenComparing(Child::getAge))
				.limit(2)
				.collect(Collectors.toList());
	}

	// TODO NOTE! DO NOT USE STREAMS
	// TODO refactor Map computation with Map merge method and you can
	// TODO eventually change loop by Iterator forEach method
	// TODO use static reference to the Integer sum method
	public Map<String, Long> countWord(List<String> words) {
		Map<String, Long> count = new HashMap<>();
		words.forEach(word -> count.merge(word, 1L, Long::sum));
		return count;
	}

	// TODO NOTE! USE STREAMS
	// TODO use Collectors.groupingBy with Collectors.counting in second argument
	public  Map<String, Long> countWordStream(List<String> words) {

		return words.stream()
				.collect(Collectors.groupingBy(
						Function.identity(),
						Collectors.counting()));
	}

	// TODO use Collectors.collectingAndThen, Collectors.groupingBy with Collectors.counting in second argument
	// TODO to complete the refactor use max map and orElse
	public int retrieveMostCommonAge() {
		return persons.stream()
				.collect(Collectors.collectingAndThen(
						Collectors.groupingBy(Person::getAge, Collectors.counting()),
						Map::entrySet))
				.stream()
				.max(Comparator.comparing(Map.Entry::getValue))
				.map(Map.Entry::getKey)
				.orElse(0);

	}


}
