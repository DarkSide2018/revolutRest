package dao;

import org.h2.tools.RunScript;
import util.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class H2DaoFactory {
    private static final String dbDriver = Util.getStringProperty("dbDriver");
    private static final String dbUrl = Util.getStringProperty("dbUrl");
    private static final String dbUser = Util.getStringProperty("dbUser");
    private static final String dbPassword = Util.getStringProperty("dbPassword");
    private static Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClass().getName());

    public void populateTestData() {
        logger.info("Populating Test User Table and data ..... ");
        Connection conn = null;
        try {
            conn = H2DaoFactory.getConnection();
            RunScript.execute(conn, new FileReader("src/main/resources/populateDB.sql"));
        } catch (SQLException e) {
            logger.warning("populateTestData(): Error populating user data: ");

        } catch (FileNotFoundException e) {
            logger.warning("populateTestData(): Error finding test script file ");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);

    }
}
