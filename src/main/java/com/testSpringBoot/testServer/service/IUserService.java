package com.testSpringBoot.testServer.service;

import com.testSpringBoot.testServer.model.User;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 8/30/2020
 */
public interface IUserService {

    List<User> getAllUsers();
}
