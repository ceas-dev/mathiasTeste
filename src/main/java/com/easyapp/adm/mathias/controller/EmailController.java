package com.easyapp.adm.mathias.controller;

import com.easyapp.adm.mathias.model.Email;
import com.easyapp.adm.mathias.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {
    @Autowired
    private EmailService service;

    @PostMapping(value = "send", consumes = "application/json")
    public ResponseEntity<String> sendEmail(@RequestBody Email email){
        return service.sendEmail(email);
    }

}
