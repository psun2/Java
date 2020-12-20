package com.lec.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String Home(Model model, Locale locale) {
        log.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String format = dateFormat.format(date);

        model.addAttribute("serverTime", format);

        return "home";
    }

    @GetMapping("/admin/admin")
    public void admin(Model model, Locale locale) {
        log.info("Welcome hello! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String format = dateFormat.format(date);

        model.addAttribute("serverTime", format);
    }

    @GetMapping("/member/member")
    public void member(Model model, Locale locale) {
        log.info("Welcome hello! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String format = dateFormat.format(date);

        model.addAttribute("serverTime", format);
    }

    @GetMapping("/logout")
    public void logout() {
    }

    @PostMapping("/logout")
    public void logoutOk() {
    }

    @GetMapping("accessError")
    public void accessError() {
    }

}
