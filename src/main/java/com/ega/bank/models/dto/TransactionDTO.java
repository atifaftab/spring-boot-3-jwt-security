package com.ega.bank.models.dto;

import com.ega.bank.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Integer id;
    private LocalDate updatedDate;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private Account account;
}
