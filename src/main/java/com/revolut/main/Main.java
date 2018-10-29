package com.revolut.main;


import com.revolut.servlets.BankServlet;
import com.revolut.servlets.AccountServlet;
import com.revolut.util.Util;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.logging.Logger;

public class Main {
    private static final int PORT = Util.getIntegerProperty("port", 9998);

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        BankServlet bankServlet = new BankServlet();
        AccountServlet accountServlet = new AccountServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(bankServlet), "/bank");
        context.addServlet(new ServletHolder(accountServlet), "/account");
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
