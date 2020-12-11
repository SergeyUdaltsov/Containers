package com.testSpringBoot.testServer.dao;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 12/9/2020
 */
public interface IDao<T> {

    List<T> getAllItems();
}
