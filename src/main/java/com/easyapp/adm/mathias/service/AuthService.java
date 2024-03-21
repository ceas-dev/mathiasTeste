package com.easyapp.adm.mathias.service;

import com.easyapp.adm.mathias.model.TokenInfo;
import com.easyapp.adm.mathias.repository.AuthRepository;
import com.easyapp.adm.mathias.utils.LogUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class AuthService {

    @Autowired
    AuthRepository repository;

    public Optional<TokenInfo> verifyToken(String token){
        try {
            return Optional.of(repository.verifyToken(token));
        } catch (FirebaseAuthException e) {
            LogUtils.log("Erro ao verificar log: " + e);
            return Optional.empty();
        }
    }


    public CompletableFuture<ResponseEntity<String>> generateLinkResetPassword(String email){
        var apiFuture = repository.generateLinkResetPassword(email);
        var completableFuture = new CompletableFuture<ResponseEntity<String>>();
        apiFuture.addListener(()->{
            ResponseEntity<String> response;
            try {
                String link = apiFuture.get(1, TimeUnit.MINUTES);
                if(link == null || link.isEmpty()){
                    response = ResponseEntity.notFound().build();
                }else{
                    response = ResponseEntity.ok(link);
                }
            } catch (InterruptedException | ExecutionException e) {
                response = ResponseEntity.internalServerError().body(e.getMessage());
            } catch (TimeoutException e) {
                response = ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(e.getMessage());
            }
            completableFuture.complete(response);
        }, Runnable::run);
        return completableFuture;
    }

    private FirebaseAuth auth(){
        return FirebaseAuth.getInstance();
    }
}
