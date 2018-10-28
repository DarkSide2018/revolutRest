package util;

import dao.H2DaoFactory;
import dao.ScoreDaoImpl;
import dao.UserDaoImpl;
import dao.interFaces.ScoreDao;
import dao.interFaces.UserDao;
import dao.BankDao;
import service.ScoreService;

public class AppManager {
    private static final ScoreDao scoreDao = new ScoreDaoImpl();
    private static final UserDao userDao = new UserDaoImpl();
    private static final BankDao bankDao = new BankDao();
    private static final ScoreService scoreService = new ScoreService();

    public AppManager() {

    }

    public static ScoreDao getScoreDao() {
        return scoreDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static BankDao getBankDao() {
        return bankDao;
    }

    public static ScoreService getScoreService() {
        return scoreService;
    }
    static {
        H2DaoFactory.populateTestData();
    }
}
