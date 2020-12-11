package com.testSpringBoot.testServer.utils;

import java.util.Map;

/**
 * @author Serhii_Udaltsov on 9/26/2020
 */
public class CollectionUtils {

    public static boolean isEmpty(Map<Object, Object> map) {
        return  (map == null || map.size() == 0);
    }
}
