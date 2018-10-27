import dao.H2DaoFactory;
import dao.UserDaoImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.BankServlet;
import servlets.ScoreServlet;
import util.Util;

import java.util.logging.Logger;

public class Main {
    private static final int PORT = Util.getIntegerProperty("port",9998);

    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        H2DaoFactory.populateTestData();
        BankServlet bankServlet = new BankServlet();
        ScoreServlet scoreServlet = new ScoreServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(bankServlet), "/bank");
        context.addServlet(new ServletHolder(scoreServlet), "/score");
        Server server = new Server(PORT);
        server.setHandler(context);
        try {
            server.start();
            logger.info("Server starting");
            server.join();
        } catch (Exception e) {
            logger.warning("Problem with start");
        }

    }
}
