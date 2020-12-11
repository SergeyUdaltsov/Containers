package com.testSpringBoot.testServer.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.NoSuchElementException;

/**
 * @author Serhii_Udaltsov on 8/30/2020
 */
public enum Gender {
    MALE("male"), FEMALE("female");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonCreator
    public static Gender fromValue(String value) {
        Gender[] values = Gender.values();
        for (Gender gender : values) {
            if (value.equalsIgnoreCase(gender.getName())) {
                return gender;
            }
        }
        throw new NoSuchElementException(String.format("Gender with name %s not found", value));
    }
}
