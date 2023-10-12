package com.project.micro.creditcard.repo;

import com.project.micro.creditcard.model.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ICreditCardRepo extends ReactiveMongoRepository<CreditCard,String> {

    Mono<CreditCard>findByIdCustomerAndStatus(String idCustomer,boolean status);

}
