package com.testSpringBoot.testServer.service.impl;

import com.testSpringBoot.testServer.dao.IUserDao;
import com.testSpringBoot.testServer.model.User;
import com.testSpringBoot.testServer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 8/30/2020
 */
@Service
public class UserService implements IUserService {
    private IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
