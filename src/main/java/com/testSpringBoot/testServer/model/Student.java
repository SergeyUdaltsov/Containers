package com.testSpringBoot.testServer.model;

/**
 * @author Serhii_Udaltsov on 9/19/2020
 */
public class Student extends Human {

    String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int getAge() {
        return 100;
    }
}
