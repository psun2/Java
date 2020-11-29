package com.lec.sts16_interceptor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

	// 테스트 계정
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";

	@GetMapping("/login")
	public void login() {
	}

	@PostMapping("/loginOk")
	public String loginOk(String userId, String userPw, HttpSession session, RedirectAttributes redirectAttributes) {

		log.error("userId: {}", userId);
		log.error("userPw: {}", userPw);

		// 기존의 로그인 정보 제거
		if (session.getAttribute("userId") != null)
			session.removeAttribute("userId");

		String page = "/user/logfail";
		;

		if (userId.equals(ADMIN_ID)) {

			if (userPw.equals(ADMIN_PW)) {
				// 로그인 성공시 세션 저장
				session.setAttribute("userId", userId);

				page = "redirect:/board/list";

				if (session.getAttribute("prevUrl") != null) {
					page = "redirect:" + session.getAttribute("prevUrl");
					session.removeAttribute("prevUrl");
				}

			}
		}
		
		log.info(page);

		return page;
	}

	@GetMapping("/logout")
	public void logOut(HttpSession session) {
		session.invalidate();
	}

}
