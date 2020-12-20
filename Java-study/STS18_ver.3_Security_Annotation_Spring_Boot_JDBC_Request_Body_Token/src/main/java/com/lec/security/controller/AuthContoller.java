package com.lec.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthContoller {

    @GetMapping("/auth/login")
    public void login() {
    }


}
