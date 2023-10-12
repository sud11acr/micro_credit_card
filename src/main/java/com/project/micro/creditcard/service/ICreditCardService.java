package com.project.micro.creditcard.service;

import com.project.micro.creditcard.integration.CreditCardRequest;
import com.project.micro.creditcard.integration.CreditCardResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditCardService {

    Mono<CreditCardResponse>findById(String id);
    Mono<CreditCardResponse>findByIdCustomer(String idCustomer);
    Flux<CreditCardResponse> findAll();
    Mono<CreditCardResponse>create(Mono<CreditCardRequest> creditCardRequest);
    Mono<CreditCardResponse>update(Mono<CreditCardRequest> creditCardRequest,String id);
    Mono<Void>delete(String id);
}
