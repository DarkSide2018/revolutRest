package servlets;

import model.UserTransaction;
import service.BankService;
import util.AppManager;
import util.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BankServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger();
    private final BankService bankService = AppManager.getBankService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.getWriter().println("HelloWorldGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String fromScore = req.getParameter("fromScore");
        String toScore = req.getParameter("toScore");
        String currency = req.getParameter("currency");
        String amount = req.getParameter("amount");
        try {
            UserTransaction userTransaction = new UserTransaction(currency, new BigDecimal(amount), Long.valueOf(fromScore), Long.valueOf(toScore));
            bankService.transferMoney(userTransaction);
        } catch (SQLException e) {
            LOGGER.warning("problem with transfer with" + e.getMessage());
        }
        resp.getWriter().println("successTransfer");
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
