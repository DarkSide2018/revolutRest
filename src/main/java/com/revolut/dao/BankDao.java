package com.revolut.dao;

import com.revolut.model.Account;
import com.revolut.model.UserTransaction;
import com.revolut.util.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class BankDao {
    private static final Logger LOGGER = LoggerFactory.getLogger();
    private final static String UPDATE_ACC_BALANCE = "UPDATE Score SET balance = ? WHERE id = ? ";
    private final static String LOCK_ACC_BY_ID = "SELECT * FROM Score WHERE id = ? FOR UPDATE";


    public void transferMoney(UserTransaction userTransaction) throws SQLException {
        Connection conn = null;
        PreparedStatement lockStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;
        Account fromAccount = null;
        Account toAccount = null;
        try {
            conn = H2DaoFactory.getConnection();
            conn.setAutoCommit(false);
            lockStmt = conn.prepareStatement(LOCK_ACC_BY_ID);
            lockStmt.setLong(1, userTransaction.getFromScoreId());
            rs = lockStmt.executeQuery();
            if (rs.next()) {
                fromAccount = new Account(rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getBigDecimal("balance"),
                        rs.getString("currencyCode"));
            }
            lockStmt = conn.prepareStatement(LOCK_ACC_BY_ID);
            lockStmt.setLong(1, userTransaction.getToScoreId());
            rs = lockStmt.executeQuery();
            if (rs.next()) {
                toAccount = new Account(rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getBigDecimal("balance"),
                        rs.getString("currencyCode"));

            }
            if (fromAccount == null || toAccount == null) {
                throw new Exception("Fail to lock both Scores for write");
            }
            if (!fromAccount.getCurrencyCode().equals(userTransaction.getCurrencyCode())) {
                throw new Exception(
                        "Fail to transfer Fund, transaction ccy are different from source/destination");
            }
            if (!fromAccount.getCurrencyCode().equals(toAccount.getCurrencyCode())) {
                throw new Exception(
                        "Fail to transfer Fund, the source and destination Account are in different currency");
            }
            BigDecimal fromScoreLeftOver = fromAccount.getBalance().subtract(userTransaction.getAmount());
            if (fromScoreLeftOver.longValue() < 0L) {
                throw new Exception("Not enough money ");
            }
            updateStmt = conn.prepareStatement(UPDATE_ACC_BALANCE);
            updateStmt.setBigDecimal(1, fromScoreLeftOver);
            updateStmt.setLong(2, userTransaction.getFromScoreId());
            updateStmt.addBatch();
            updateStmt.setBigDecimal(1, toAccount.getBalance().add(userTransaction.getAmount()));
            updateStmt.setLong(2, userTransaction.getToScoreId());
            updateStmt.addBatch();
            updateStmt.executeBatch();
            conn.commit();
        } catch (Exception se) {
            LOGGER.warning("problem with transfer: " + se);
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException re) {
                LOGGER.warning("problem with rollback");
            }
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
