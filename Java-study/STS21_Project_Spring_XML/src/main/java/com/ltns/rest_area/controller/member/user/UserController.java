package com.ltns.rest_area.controller.member.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/user")
public class UserController {

    @GetMapping("/info")
    public void info() {}

    @GetMapping("/mypage")
    public void mypage() {}

}
