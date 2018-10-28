package com.revolut.util;

import com.revolut.dao.H2DaoFactory;
import com.revolut.dao.ScoreDaoImpl;
import com.revolut.dao.UserDaoImpl;
import com.revolut.dao.interFaces.ScoreDao;
import com.revolut.dao.interFaces.UserDao;
import com.revolut.dao.BankDao;
import com.revolut.service.ScoreService;

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
