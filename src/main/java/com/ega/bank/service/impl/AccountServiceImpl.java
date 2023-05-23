package com.ega.bank.service.impl;

import com.ega.bank.config.JwtService;
import com.ega.bank.models.Account;
import com.ega.bank.repository.AccountRepository;
import com.ega.bank.service.AccountService;
import com.ega.bank.tools.Helper;
import com.ega.bank.user.User;
import com.ega.bank.user.UserRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ega.bank.tools.Helper.getTenDigitAccountNumber;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final Helper helper;


//    private Gson gson = new Gson();

    @Override
    public BigDecimal getBalanceByUserId(HttpServletRequest request) {

        int userIdFromRequest = helper.getUserIdFromRequest(request);
        return fetchBalanceByUserId(userRepository.findById(userIdFromRequest).get());
    }

    @Override
    public Account creatBankAccount(BigDecimal balance, User user, String accountType) {
        //get 10 digit random number for account
        String digitAccountNumber = getTenDigitAccountNumber();

        var account = Account.builder()
                .balance(balance)
                .user(user)
                .accountType(accountType)
                .createdDate(LocalDate.now())
                .status("active")
                .updatedDate(LocalDate.now())
                .accountNumber(digitAccountNumber)
                .build();
        return accountRepository.save(account);
    }

    @Override
    public Account getBankAccountByUserId(HttpServletRequest request) {
        int userIdFromRequest = helper.getUserIdFromRequest(request);
        User user = userRepository.findById(userIdFromRequest).get();
        return fetchBankAccountByUser(user);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }


    private void create(Account account){
        accountRepository.save(account);
    }
    private BigDecimal fetchBalanceByUserId(User user){return accountRepository.findBalanceByUserId(user);}
    private Account fetchBankAccountByUser(User user){return accountRepository.findByUser(user);}
}
