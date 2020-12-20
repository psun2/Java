package com.ltns.rest_area.controller.auth.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/user")
public class UserController {

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/lookupid")
    public void lookupid() {}

    @GetMapping("/lookuppw")
    public void lookuppw() {}

    @GetMapping("/join")
    public void join() {}

}
