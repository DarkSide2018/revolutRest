package service;

import dao.interFaces.ScoreDao;
import model.Score;
import util.AppManager;

public class ScoreService {

    private final ScoreDao scoreDao = AppManager.getScoreDao();

    public Score getScoreById(long scoreId){
        return scoreDao.getScoreById(scoreId);
    }
}
