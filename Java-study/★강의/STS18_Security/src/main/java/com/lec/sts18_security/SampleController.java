package com.lec.sts18_security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*")
public class SampleController {
	@GetMapping("/all")
	public void doAll() {
		System.out.println("doAll() : 누구나 접근 가능");
	}
	
	@GetMapping("/member")
	public void doMember() {
		System.out.println("doMember() : 로그인한 member 만 접근가능");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		System.out.println("doAdmin() : admin 만 접근 가능");
	}
}
