package com.project.micro.creditcard.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreditCardDto {

    private String idCreditCard;
    private String idCustomer;
    private String cardType;
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;
}
