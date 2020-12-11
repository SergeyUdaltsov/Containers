package com.testSpringBoot.testServer.executor.impl;

import com.testSpringBoot.testServer.constants.CommonConstants;
import com.testSpringBoot.testServer.model.User;
import com.testSpringBoot.testServer.service.IUserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 8/30/2020
 */
@Component
public class GetUsersExecutor extends AbstractExecutor<Object, List<User>> {
    private IUserService userService;

    public GetUsersExecutor(IUserService userService) {
        super(Object.class);
        this.userService = userService;
    }

    @Override
    List<User> executeRequest(Object request, Map<String, String> requestParams) {
        return userService.getAllUsers();
    }

    @Override
    public String getSupportedUri() {
        return CommonConstants.GET_USERS;
    }
}
