package com.project.micro.creditcard.service.impl;

import com.project.micro.creditcard.integration.CreditCardRequest;
import com.project.micro.creditcard.integration.CreditCardResponse;
import com.project.micro.creditcard.mapper.CreditCardMapper;
import com.project.micro.creditcard.model.CreditCard;
import com.project.micro.creditcard.repo.ICreditCardRepo;
import com.project.micro.creditcard.service.ICreditCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public class CreditCardServiceImpl implements ICreditCardService {

    @Autowired
    private ICreditCardRepo repo;

    @Override
    public Mono<CreditCardResponse> findById(String id) {
        return repo.findById(id).map(p-> CreditCardMapper.toCreditCardResponse(p));
    }

    @Override
    public Flux<CreditCardResponse> findAll() {
        return repo.findAll().map(CreditCardMapper::toCreditCardResponse);
    }

    @Override
    public Mono<CreditCardResponse> create(Mono<CreditCardRequest> creditCardRequest) {
        return creditCardRequest
                .map(p->CreditCardMapper.toCreditCard(p))
                .flatMap(m->{
                    m.setRegistrationDate(new Date());
                    m.setModificationDate(new Date());
                    m.setStatus(true);
                    return repo.save(m);
                })
                .map(f->CreditCardMapper.toCreditCardResponse(f));

    }

    @Override
    public Mono<CreditCardResponse> update(Mono<CreditCardRequest> creditCardRequest, String id) {
        Mono<CreditCard> monoReq=creditCardRequest.map(CreditCardMapper::toCreditCard);
        Mono<CreditCard> monoBd=repo.findById(id);
        return monoBd.zipWith(monoReq,(bd,req)->{
            BeanUtils.copyProperties(req,bd);
            bd.setIdCreditCard(id);
            return bd;
        }).flatMap(repo::save)
                .map(CreditCardMapper::toCreditCardResponse);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }
}
