package com.testSpringBoot.testServer.model;

/**
 * @author Serhii_Udaltsov on 9/19/2020
 */
public class Human {

    String email;
    int age;

    public Human() {
    }

    public Human(String email, int age) {
        this.email = email;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return 200;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
