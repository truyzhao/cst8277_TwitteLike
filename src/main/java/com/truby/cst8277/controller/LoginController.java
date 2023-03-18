package com.truby.cst8277.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;



@RestController
public class LoginController {

    @GetMapping( "/" )
    public Mono<String> home() {
        return Mono.just("Hello, welcome to TwitterLike App");
    }

    @GetMapping( "/login" )
    public Mono<String> loginPage() {
        return Mono.just("login Here!");
    }

    @PreAuthorize( "hasRole('ADMIN')" )
    @GetMapping( "/user" )
    public Mono<ResponseEntity<String>> adminResource() {

        return Mono.just(new ResponseEntity<>("Hello, ADMIN!", HttpStatus.OK));
    }

    @PreAuthorize( "hasRole('producer')" )
    @GetMapping("/message")
    public Mono<ResponseEntity<String>> producerPage() {

        return Mono.just(new ResponseEntity<>("Hello, Producer!", HttpStatus.OK));
    }

    @PreAuthorize( "hasRole('subscriber')" )
    @GetMapping("/subscription")
    public Mono<ResponseEntity<String>> subscriberPage() {
        return Mono.just(new ResponseEntity<>("Hello, Subscriber!", HttpStatus.OK));
    }

    @GetMapping( "/register" )
    public Mono<String> registerPage() {
        return Mono.just("Create your own account!");
    }
}
