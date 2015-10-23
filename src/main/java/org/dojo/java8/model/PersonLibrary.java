package org.dojo.java8.model;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PersonLibrary {

    static public List<Person> getPersons(String fileName) {
        try {
            InputStream resourceAsStream = PersonLibrary.class.getResourceAsStream(fileName);
            return new ObjectMapper().readValue(resourceAsStream, PersonDirectory.class).personList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

