package servlets;

import model.UserTransaction;
import dao.BankDao;
import util.AppManager;
import util.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

public class BankServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger();
    private final BankDao bankService = AppManager.getBankDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String fromScore = req.getParameter("fromScore");
            if (fromScore == null) throw new Exception("fromScore empty");
            // analog for another fields
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
}
