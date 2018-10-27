package util;

import dao.H2DaoFactory;
import dao.ScoreDaoImpl;
import dao.UserDaoImpl;
import dao.interFaces.ScoreDao;
import dao.interFaces.UserDao;
import service.BankService;
import service.ScoreService;

public class AppManager {
    private static final ScoreDao scoreDao = new ScoreDaoImpl();
    private static final UserDao userDao = new UserDaoImpl();
    private static final BankService bankService = new BankService();
    private static final ScoreService scoreService = new ScoreService();
    public AppManager() {
        H2DaoFactory.populateTestData();
    }

    public static ScoreDao getScoreDao() {
        return scoreDao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static BankService getBankService() {
        return bankService;
    }

    public static ScoreService getScoreService() {
        return scoreService;
    }
}
