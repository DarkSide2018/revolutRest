package com.revolut.service;

import com.revolut.dao.interFaces.ScoreDao;
import com.revolut.model.Account;
import com.revolut.util.AppManager;

public class ScoreService {

    private final ScoreDao scoreDao;

    public ScoreService() {
        this.scoreDao = AppManager.getScoreDao();
    }

    public Account getScoreById(long scoreId){
        return scoreDao.getScoreById(scoreId);
    }
}
