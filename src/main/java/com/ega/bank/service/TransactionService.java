package com.ega.bank.service;

import com.ega.bank.models.Transaction;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionListByAccount(HttpServletRequest request);

    Boolean credit(BigDecimal amount, HttpServletRequest request);

    Boolean debit(BigDecimal amount, HttpServletRequest request);

}
