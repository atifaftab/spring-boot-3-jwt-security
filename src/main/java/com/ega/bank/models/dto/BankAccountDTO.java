package com.ega.bank.models.dto;

import com.ega.bank.user.User;
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
public class BankAccountDTO {

    private Integer id;
    private String accountNumber;
    private String accountHolderName;
    private BigDecimal balance;
    private String accountType;
    private LocalDate createdDate;
    private String status;
    private LocalDate updatedDate;
    private User user;

}
