package com.testSpringBoot.testServer.executor.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.testSpringBoot.testServer.executor.IExecutor;
import com.testSpringBoot.testServer.utils.JsonUtils;
import com.testSpringBoot.testServer.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Serhii_Udaltsov on 8/29/2020
 */
@Component
public abstract class AbstractExecutor<REQUEST, RESPONSE> implements IExecutor {

    public static final String DATA = "data";
    @Autowired
    private ObjectMapper objectMapper;
    private Class<REQUEST> requestClass;

    public AbstractExecutor(Class<REQUEST> requestClass) {
        this.requestClass = requestClass;
    }

    @Override
    public void execute(HttpServletRequest rawRequest, HttpServletResponse response) throws IOException {
        Map<String, String> requestParams = rawRequest.getParameterMap().entrySet().stream()
                .filter(entry -> !DATA.equalsIgnoreCase(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()[0]));
        String dataJson = rawRequest.getParameter(DATA);
        REQUEST request = !StringUtils.hasText(dataJson)
                ? null
                : JsonUtils.parseJson(dataJson, new TypeReference<REQUEST>() {
        });
        RESPONSE executorResponse = executeRequest(request, requestParams);
        ObjectWriter writer = objectMapper.writer();
        String responseJson = writer.writeValueAsString(executorResponse);
        response.getWriter().write(responseJson);
    }

    abstract RESPONSE executeRequest(REQUEST request, Map<String, String> requestParams) throws IOException;

    @Override
    public String getSupportedUri() {
        throw new UnsupportedOperationException("Not implemented handler");
    }
}
