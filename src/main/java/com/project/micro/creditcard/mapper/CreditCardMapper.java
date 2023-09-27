package com.project.micro.creditcard.mapper;

import com.project.micro.creditcard.integration.CreditCardRequest;
import com.project.micro.creditcard.integration.CreditCardResponse;
import com.project.micro.creditcard.model.CreditCard;
import org.springframework.beans.BeanUtils;

public class CreditCardMapper {

    public CreditCardMapper(){

    }

    public static CreditCardResponse toCreditCardResponse(CreditCard creditCard){
        CreditCardResponse creditCardResponse= new CreditCardResponse();
        BeanUtils.copyProperties(creditCard,creditCardResponse);
        return creditCardResponse;
    }

    public static CreditCard toCreditCard(CreditCardRequest creditCardRequest){
        CreditCard creditCard=new CreditCard();
        BeanUtils.copyProperties(creditCardRequest,creditCard);
        return creditCard;
    }
}
