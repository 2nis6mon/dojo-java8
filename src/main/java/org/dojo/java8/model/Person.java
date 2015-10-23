package org.dojo.java8.model;

import java.util.List;

public class Person {

    private String name;

    private String surname;

    private int age;

    private String language;

    private boolean married;

    private List<Child> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name +
                "\",\"surname\":\"" + surname +
                "\",\"age\":" + age +
                ",\"language\":\"" + language +
                "\",\"married\":" + married +
                ",\"children\":" + children +
                "}";
    }

}
