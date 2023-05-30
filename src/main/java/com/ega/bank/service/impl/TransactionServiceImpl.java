package com.ega.bank.service.impl;

import com.ega.bank.models.Account;
import com.ega.bank.models.Transaction;
import com.ega.bank.repository.TransactionRepository;
import com.ega.bank.service.AccountService;
import com.ega.bank.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public List<Transaction> getTransactionListByAccount(HttpServletRequest request) {

        Account account = accountService.getBankAccountByUserId(request);
        List<Transaction> transactions = account.getTransactions();
        return transactions;
    }

    @Override
    public Boolean credit(BigDecimal amount, HttpServletRequest request) {

        Account account = accountService.getBankAccountByUserId(request);
        BigDecimal newBalance = account.getBalance().add(amount);
        var transaction = Transaction.builder()
                .updatedDate(LocalDate.now())
                .type("CREDIT")
                .amount(amount)
                .balance(newBalance)
                .account(account)
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);
        account.setUpdatedDate(LocalDate.now());
        account.setBalance(newBalance);
        account = accountService.update(account);
        return true;
    }

    @Override
    public Boolean debit(BigDecimal amount, HttpServletRequest request) {
        Account account = accountService.getBankAccountByUserId(request);
        if (amount.compareTo(account.getBalance()) == -1) {
            BigDecimal newBalance = account.getBalance().subtract(amount);
            var transaction = Transaction.builder()
                    .updatedDate(LocalDate.now())
                    .type("DEBIT")
                    .amount(amount)
                    .balance(newBalance)
                    .account(account)
                    .build();

            transactionRepository.save(transaction);
            account.setUpdatedDate(LocalDate.now());
            account.setBalance(newBalance);
            account = accountService.update(account);
            return true;
        } else return false;
    }
}
