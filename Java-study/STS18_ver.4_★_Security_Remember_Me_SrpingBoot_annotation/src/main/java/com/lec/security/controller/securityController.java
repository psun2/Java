package com.lec.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class securityController {

    @GetMapping("/auth/login") // 컨트롤러는 기본적으로 포워딩이기때문에....
    // 현재와 다른 주소로 보내주고싶다면 redirect ...를 
    public String login(Authentication authentication){
    	
    	log.info("Authentication : {}", authentication);
    	
    	if(authentication != null) // 리맴버 미로 기억 되는 인증 정보가 있다면...
    		return "redirect:/"; // 홈 화면으로..
    	
    	return "/auth/login"; // 없다면 로그인 화면을 보여주도록 합니다.
    }

    @GetMapping("/member/member")
	public void member() {}

    @GetMapping("/admin/admin")
	public void admin() {}

}
