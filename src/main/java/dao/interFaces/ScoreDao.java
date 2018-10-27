package dao.interFaces;

import model.Score;

import java.util.List;

public interface ScoreDao {
    Score getScoreById(long scoreId);
    List<Score> getScoreByCurrencyCode(String code);
    List<Score> getAllScores();
    void insertScore(Score score);
    void updateScore(Long scoreId, Score score);
    void deleteScore(long scoreId);
}
