package com.easyapp.adm.mathias.model;

import com.google.firebase.auth.FirebaseToken;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenInfo {
    private String uid;
    private boolean emailVerified;
    private String authTime;
    private Long expires;
    private String value;

    public TokenInfo(FirebaseToken firebaseToken, String value) {
        this.uid = firebaseToken.getUid();
        this.emailVerified = firebaseToken.isEmailVerified();
        this.authTime = firebaseToken.getClaims().get("auth_time").toString();
        this.expires =  Long.valueOf(firebaseToken.getClaims().get("exp").toString());
        this.value = value;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getAuthTime() {
        return authTime;
    }

    public void setAuthTime(String authTime) {
        this.authTime = authTime;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getExpireDate(){
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date(expires * 1000));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
