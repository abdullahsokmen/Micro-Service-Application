package com.abdullah.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/auth")
    public ResponseEntity<String> fallbackAuth(){
        return ResponseEntity.ok("Auth servisi su anda hizmet verememektedir lütfen daha sonra tekrar deneyin");
    }

    @GetMapping("/product")
    public ResponseEntity<String> fallbackProduct(){
        return ResponseEntity.ok("Product servisi su anda hizmet verememektedir lütfen daha sonra tekrar deneyin");
    }
    @GetMapping("/sale")
    public ResponseEntity<String> fallbackSale(){
        return ResponseEntity.ok("Sale servisi su anda hizmet verememektedir lütfen daha sonra tekrar deneyin");
    }

    @GetMapping("/user")
    public ResponseEntity<String> fallbackUser(){
        return ResponseEntity.ok("User servisi su anda hizmet verememektedir lütfen daha sonra tekrar deneyin");
    }
}
