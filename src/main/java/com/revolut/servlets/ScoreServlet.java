package com.revolut.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.model.Score;
import com.revolut.service.ScoreService;
import com.revolut.util.AppManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ScoreServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final ScoreService scoreService;

    public ScoreServlet() {
        this.scoreService = AppManager.getScoreService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Score scoreId = scoreService.getScoreById(Long.valueOf(req.getParameter("scoreId")));
        resp.getWriter().print(objectMapper.writeValueAsString(scoreId));
    }
}
