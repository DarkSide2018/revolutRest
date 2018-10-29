package com.revolut.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.model.Account;
import com.revolut.service.ScoreService;
import com.revolut.util.AppManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final ScoreService scoreService;

    public AccountServlet() {
        this.scoreService = AppManager.getScoreService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account accountId = scoreService.getScoreById(Long.valueOf(req.getParameter("accountId")));
        resp.getWriter().print(objectMapper.writeValueAsString(accountId));
    }
}
