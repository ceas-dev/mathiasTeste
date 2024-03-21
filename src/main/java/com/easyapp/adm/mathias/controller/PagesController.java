package com.easyapp.adm.mathias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
    @GetMapping("/")
    public String index(){
        return "/app/pages/main/index.html";
    }
}
