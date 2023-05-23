package com.ega.bank.repository;

import com.ega.bank.models.Account;
import com.ega.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.account=:account")
    Optional<List<Transaction>> transactionListByAccount(Account account);
}
