package com.revolut.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
public class Account {
    private long id;
    private final long userId;
    private final BigDecimal balance;
    private final String currencyCode;
  }
