package com.lec.securiry.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController { 
	// 서버 동작시 에러가 나는이유 : security에 관해 주석을 여러가지 달아 놨는데
	// <security:jdbc-user-service /> 부분에서 에러가 나니 
	// security-context.xml 파일에서 잠시 잘라내서 서버 동작을 시키시면 됩니다.

	@PostMapping("/testBody")
	public User test(@RequestBody User user, HttpServletRequest request) {
		
		log.info("_csrf 토큰 정보 : ", (String)request.getSession().getAttribute("_csrf"));
		log.info("csrf 토큰 정보 : ", (String)request.getSession().getAttribute("csrf"));
		log.info("JSESSIONID 토큰 정보 : ", (String)request.getSession().getAttribute("JSESSIONID"));
		
		Cookie[] cookies = request.getCookies();
		List<Cookie> cookieList = Arrays.asList(cookies);
		cookieList.forEach(cookie -> {
			log.info("쿠키 정보 => {} : {}", cookie.getName(), cookie.getValue());
			log.info("comment : {}, Domaiin : {}", cookie.getComment(), cookie.getDomain());
			log.info("path : {}, MaxAge : {}", cookie.getPath(), cookie.getMaxAge());
			log.info("secure : {}, Version : {}", cookie.getSecure(), cookie.getVersion());
		});

		
		log.info("서버 데이터 : {}", user);
		return user;
	}
}
