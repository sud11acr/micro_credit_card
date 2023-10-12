package com.project.micro.creditcard.controller;

import com.project.micro.creditcard.integration.CreditCardRequest;
import com.project.micro.creditcard.integration.CreditCardResponse;
import com.project.micro.creditcard.service.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    @Autowired
    private ICreditCardService service;

    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<CreditCardResponse>> findById(@PathVariable String id){
        return service.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.noContent().build());

    }

    @GetMapping("/findByIdCustomer/{idCustomer}")
    public Mono<ResponseEntity<CreditCardResponse>> findByIdCustomer(@PathVariable String idCustomer){
        System.out.println("findByIdCustomer "+idCustomer);
        return service.findByIdCustomer(idCustomer).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.noContent().build());

    }


    @GetMapping("/findAll")
    public Mono<ResponseEntity<Flux<CreditCardResponse>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAll()));
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<CreditCardResponse>>save(@Validated @RequestBody  Mono<CreditCardRequest> creditCardRequest){
        return service.create(creditCardRequest)
                .map(p -> ResponseEntity.created(URI.create("/create".concat("/").concat(p.getIdCreditCard())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<CreditCardResponse>>update(@PathVariable String id,@RequestBody Mono<CreditCardRequest> creditCardRequest ){
        return service.update(creditCardRequest,id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }
}
