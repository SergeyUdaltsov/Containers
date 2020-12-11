package com.testSpringBoot.testServer.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testSpringBoot.testServer.model.User;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 8/30/2020
 */
public interface IUserDao {
    List<User> getAllUsers();
}
