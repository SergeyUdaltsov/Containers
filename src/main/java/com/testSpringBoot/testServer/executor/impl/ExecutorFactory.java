package com.testSpringBoot.testServer.executor.impl;

import com.testSpringBoot.testServer.executor.IExecutor;
import com.testSpringBoot.testServer.executor.IExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Serhii_Udaltsov on 8/29/2020
 */
@Component
public class ExecutorFactory implements IExecutorFactory {

    private Map<String, IExecutor> executors;

    @Autowired
    public ExecutorFactory(List<IExecutor> executors) {
        this.executors = executors.stream()
                .collect(Collectors.toMap(IExecutor::getSupportedUri, Function.identity()));
    }

    @Override
    public IExecutor getExecutor(HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        IExecutor executor = executors.get(requestURI);
        if (executor == null) {
            throw new RuntimeException(String.format("Executor not found for requestUri %s", requestURI));
        }
        return executor;
    }
}
