package com.example.brunofrani.photoapp.classes;

/**
 * Created by Bruno Frani on 27/08/2017.
 */

public class UserSettings {


    String name;
    int age;
    String gender;

    public UserSettings(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserSettings() {

    }
}
