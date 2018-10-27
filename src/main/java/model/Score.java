package model;

import java.math.BigDecimal;

public class Score {
    private long id;
    private long userId;
    private BigDecimal balance;
    private String currencyCode;

    public Score(long id, long userId, BigDecimal balance, String currencyCode) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    public Score(long userId, BigDecimal balance, String currencyCode) {
        this.userId = userId;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
