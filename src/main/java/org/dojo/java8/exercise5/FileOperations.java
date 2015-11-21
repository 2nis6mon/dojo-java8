package org.dojo.java8.exercise5;

import org.dojo.java8.model.Address;
import org.dojo.java8.model.Role;
import org.dojo.java8.model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FileOperations {

    //TODO Replace By Files.lines, use static method reference
    public static List<User> loadUsersFromCsv(Path csvPath) {
        try  {
            return Files.lines(csvPath)
                    .skip(1)
                    .map(FileOperations::lineToUser)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //TODO Replace by Files.walk and remove visitor. Use Optional.orElseThrow for throw FileNotFoundException
    public static Path findRecursivelyFileByName(String path, String fileName) throws IOException {
        return Files.walk(Paths.get(path), FileVisitOption.FOLLOW_LINKS)
                .filter(currentPath -> currentPath.getFileName().equals(Paths.get(fileName)))
                .findFirst()
                .orElseThrow(FileNotFoundException::new);
    }

    private static User lineToUser(String line) {
        String[] columns = line.split(",");
        User user = new User(columns[0], columns[1], columns[2]);
        user.withLogin(columns[3])
                .withPassword(columns[4])
                .withExpireDate(LocalDate.parse(columns[5], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")))
                .withRole(Role.valueOf(columns[6]))
        ;
        if (columns.length > 8) {
            user.withAddress(new Address(columns[7], columns[8], columns[9]));
        }

        return user;
    }

}

