package org.dojo.java8.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8 {

    public static List<String> test() {
        return Arrays.asList(1,2,3,4,5).stream().map(String::valueOf).collect(Collectors.toList());
    }
}
