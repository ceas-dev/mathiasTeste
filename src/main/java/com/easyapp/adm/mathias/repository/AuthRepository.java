package com.easyapp.adm.mathias.repository;

import com.easyapp.adm.mathias.model.TokenInfo;
import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    public TokenInfo verifyToken(String token) throws FirebaseAuthException {
        return new TokenInfo(auth().verifyIdToken(token), token);
    }

    public ApiFuture<String> generateLinkResetPassword(String email){
        return auth().generatePasswordResetLinkAsync(email);
    }

    private FirebaseAuth auth(){
        return FirebaseAuth.getInstance();
    }
}
