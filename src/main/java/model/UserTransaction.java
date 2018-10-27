package model;

import java.math.BigDecimal;

public class UserTransaction {

    private String currencyCode;
    private BigDecimal amount;
    private Long fromScoreId;
    private Long toScoreId;

    public UserTransaction(String currencyCode, BigDecimal amount, Long fromScoreId, Long toScoreId) {
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.fromScoreId = fromScoreId;
        this.toScoreId = toScoreId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getFromScoreId() {
        return fromScoreId;
    }

    public void setFromScoreId(Long fromScoreId) {
        this.fromScoreId = fromScoreId;
    }

    public Long getToScoreId() {
        return toScoreId;
    }

    public void setToScoreId(Long toScoreId) {
        this.toScoreId = toScoreId;
    }
}
