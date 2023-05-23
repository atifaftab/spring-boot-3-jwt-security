package com.ega.bank.service;

import com.ega.bank.models.Account;
import com.ega.bank.user.User;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public interface AccountService {

    BigDecimal getBalanceByUserId(HttpServletRequest request);

    Account creatBankAccount(BigDecimal balance, User user, String accountType);

    Account getBankAccountByUserId(HttpServletRequest request);

    Account update(Account account);

}
