package com.easyapp.adm.mathias.controller;

import com.easyapp.adm.mathias.model.TokenInfo;
import com.easyapp.adm.mathias.service.AuthService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService service;

    @GetMapping(value = "token/{token}", produces = "application/json")
    public ResponseEntity<TokenInfo> verifyToken(@PathVariable("token") String token){
        var tokenInfo = service.verifyToken(token);
        return tokenInfo.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(HttpStatus.SC_FORBIDDEN).build()
        );
    }

    @GetMapping("reset-password/{email}")
    public CompletableFuture<ResponseEntity<String>> generateLinkResetPassword(@PathVariable("email") String email){
        return service.generateLinkResetPassword(email);
    }

}
