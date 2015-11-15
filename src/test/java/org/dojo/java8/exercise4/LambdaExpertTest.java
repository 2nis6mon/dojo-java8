package org.dojo.java8.exercise4;

import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaExpertTest {

    LambdaExpert lambdaExpert = new LambdaExpert();

    @Test
    public void should_count_words() {
        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("javascript");
        words.add("java");
        words.add("scala");
        words.add("java");
        words.add("scala");

        Map<String, Long> wordCount = lambdaExpert.countWord(words);

        assertThat(wordCount).contains(
                MapEntry.entry("java", 3L),
                MapEntry.entry("javascript", 1L),
                MapEntry.entry("scala", 2L));

    }

    @Test
    public void should_count_words_stream() {
        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("javascript");
        words.add("java");
        words.add("scala");
        words.add("java");
        words.add("scala");

        Map<String, Long> wordCount = lambdaExpert.countWordStream(words);

        assertThat(wordCount).contains(
                MapEntry.entry("java", 3L),
                MapEntry.entry("javascript", 1L),
                MapEntry.entry("scala", 2L));

    }

    @Test
    public void check_retrieveMostCommonAge() {
        assertThat(lambdaExpert.retrieveMostCommonAge()).isEqualTo(76);
    }

    @Test
    public void check_firstTwoYoungerChildsByName() {
        assertThat(lambdaExpert.firstTwoYoungerChildsByName().toString()).isEqualTo("[{\"name\":\"Adèle\",\"age\":1}, {\"name\":\"Adèle\",\"age\":1}]");
    }
}
