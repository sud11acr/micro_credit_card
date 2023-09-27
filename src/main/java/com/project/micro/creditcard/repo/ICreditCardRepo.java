package com.project.micro.creditcard.repo;

import com.project.micro.creditcard.model.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditCardRepo extends ReactiveMongoRepository<CreditCard,String> {

}
