package org.dojo.java8.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Java8Test {

    @Test
    public void checkTest() {
        //Setup
        List<String> expected = Arrays.asList("1", "2", "3", "4", "5");

        // Test
        List<String> result = Java8.test();

        // Assertions
        assertThat(result).isEqualTo(expected);
    }

}