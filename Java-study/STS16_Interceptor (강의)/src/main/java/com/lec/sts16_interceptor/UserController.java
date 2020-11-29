package com.lec.sts16_interceptor;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	// 테스트 계정
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
	
	@RequestMapping("/login")  // /user/login
	public void logIn() {}
	
	@PostMapping("/loginOk")
	public String loginOk(String id, String pw, HttpSession session) {
		
		String returnUrl = "";
		
		if(session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}
		
		if(ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)) {
			// 로그인 성공
			session.setAttribute("id", id);
			
			// 원래 가고자 했던 url 이 있었다면
			String priorUrl = (String)session.getAttribute("url_prior_login");
			if(priorUrl != null) {
				returnUrl = "redirect:" + priorUrl;
				session.removeAttribute("url_prior_login");
			} else {
				returnUrl = "redirect:/board/list.do";				
			}
			
		}  else {
			// 로그인 실패
			returnUrl = "user/logfail";
		}
		
		return returnUrl;
	}
	
	
	@RequestMapping("/logout")
	public void logOut(HttpSession session) {
		session.removeAttribute("id");
	}
	
	
}




















