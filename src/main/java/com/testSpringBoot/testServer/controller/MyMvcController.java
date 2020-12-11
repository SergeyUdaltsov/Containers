package com.testSpringBoot.testServer.controller;

import com.testSpringBoot.testServer.executor.IExecutor;
import com.testSpringBoot.testServer.executor.IExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Serhii_Udaltsov on 8/28/2020
 */
@Controller()
public class MyMvcController extends HttpServlet {

    private IExecutorFactory executorFactory;

    @Autowired
    public MyMvcController(IExecutorFactory factory) {
        this.executorFactory = factory;
    }

    @Override
    @GetMapping("app/**")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    @Override
    @PostMapping("app/**")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeRequest(req, resp);
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IExecutor executor = executorFactory.getExecutor(request);
        executor.execute(request, response);
    }
}
