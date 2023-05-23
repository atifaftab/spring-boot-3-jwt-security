package com.ega.bank.repository;

import com.ega.bank.models.Account;
import com.ega.bank.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account, Integer> {

        @Query("SELECT a.balance FROM Account a WHERE a.user=:user")
        BigDecimal findBalanceByUserId(User user);

        @Query("SELECT a FROM Account a WHERE a.user=:user")
        Account findByUser(User user);

}
