package com.lec.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class CommonConroller {

    @GetMapping("/customLogin")
    public void login(String error, String logout, Model model) {
        log.info("[/customLogin]");
        log.info("error: {}", error);
        log.info("logout: {}", logout);

        if (error != null) { // 에러가 있다면
            model.addAttribute("error", "Login Error Check Your Accout");
        }

        if (logout != null) {
            model.addAttribute("logout", "Logout!!");
        }

    }

    @GetMapping("/customLogout")
    public void logout() {
        log.info("[/customLogout: GET]");


    }

    @PostMapping("/customLogout")
    public void logoutOk() {
        log.info("[/customLogout: POST]");

      
    }

    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model) {
        // Authentication  => import org.springframework.security.core.Authentication;
        // 라이브러리 injection 시 사용 가능
        log.error("access Denied : {}", auth);

        model.addAttribute("msg", "접근 권한 거부");
    }


}
