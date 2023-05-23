package com.ega.bank.rest;

import com.ega.bank.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/balance")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountService bankService;

    @GetMapping("BalanceById")
    ResponseEntity<java.math.BigDecimal> getBalanceByUserId(HttpServletRequest request){
        return ResponseEntity.ok(bankService.getBalanceByUserId(request));
    }
}
