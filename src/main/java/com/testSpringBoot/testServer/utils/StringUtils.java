package com.testSpringBoot.testServer.utils;

/**
 * @author Serhii_Udaltsov on 9/26/2020
 */
public class StringUtils {
    public static final String EMPTY = "";

    public static boolean hasText(String text) {
        return (text != null && !EMPTY.equalsIgnoreCase(text));
    }
}
