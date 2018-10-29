package com.revolut.dao.interFaces;

import com.revolut.model.Account;

import java.util.List;

public interface ScoreDao {
    Account getScoreById(long scoreId);

    List<Account> getScoreByCurrencyCode(String code);

    List<Account> getAllScores();

    void insertScore(Account account);

    void updateScore(Long scoreId, Account account);

    void deleteScore(long scoreId);
}
