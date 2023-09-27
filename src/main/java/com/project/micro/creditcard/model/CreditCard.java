package com.project.micro.creditcard.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "creditCard")
public class CreditCard {
    @Id
    private String idCreditCard;
    private String idCustomer;
    private String cardType;
    private String descriptionType;
    private BigDecimal creditLimit;
    private BigDecimal currentBalance;
    private Date registrationDate;
    private Date modificationDate;
    private Boolean status;

}
