package com.ega.bank.rest;

import com.ega.bank.models.Transaction;
import com.ega.bank.models.dto.AmountDTO;
import com.ega.bank.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("transaction")
    ResponseEntity<List<Transaction>> getTransaction(HttpServletRequest request){
        return ResponseEntity.ok(transactionService.getTransactionListByAccount(request));
    }

    @PostMapping("credit")
    ResponseEntity<Boolean> credit(@RequestBody AmountDTO amountDTO, HttpServletRequest request){
        System.out.println("--------------------------Hello world -----------------");
        return ResponseEntity.ok(transactionService.credit(amountDTO.getAmount(), request));
    }

    @PostMapping("debit")
    ResponseEntity<Boolean> debit(@RequestBody AmountDTO amountDTO, HttpServletRequest request){
        return ResponseEntity.ok(transactionService.debit(amountDTO.getAmount(), request));
    }

}
