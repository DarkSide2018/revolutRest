package com.revolut.model;

import lombok.*;

import java.math.BigDecimal;
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserTransaction {
    private String currencyCode;
    private  BigDecimal amount;
    private  Long fromScoreId;
    private Long toScoreId;
}
