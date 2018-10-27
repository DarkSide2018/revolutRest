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
        try {
        String fromScore = req.getParameter("fromScore");
        if(fromScore == null) throw new Exception("fromScore empty");
        String toScore = req.getParameter("toScore");
        String currency = req.getParameter("currency");
        String amount = req.getParameter("amount");

            UserTransaction userTransaction = new UserTransaction(currency, new BigDecimal(amount), Long.valueOf(fromScore), Long.valueOf(toScore));
            bankService.transferMoney(userTransaction);
            resp.getWriter().println("successTransfer");
        } catch (Exception e) {
            LOGGER.warning("problem with transfer with: " + e.getMessage());
        }

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
