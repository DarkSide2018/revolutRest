package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Score;
import util.AppManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ScoreServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    private final service.ScoreService scoreService = AppManager.getScoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Score scoreId = scoreService.getScoreById(Long.valueOf(req.getParameter("scoreId")));
        resp.getWriter().print(objectMapper.writeValueAsString(scoreId));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
