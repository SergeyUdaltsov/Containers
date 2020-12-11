package com.testSpringBoot.testServer.executor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Serhii_Udaltsov on 8/29/2020
 */
public interface IExecutorFactory {

    IExecutor getExecutor(HttpServletRequest request);
}
