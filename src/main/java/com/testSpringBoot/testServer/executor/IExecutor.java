package com.testSpringBoot.testServer.executor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Serhii_Udaltsov on 8/29/2020
 */
public interface IExecutor {

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

    String getSupportedUri();
}
