package com.lec.sts11_requestparameter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemController implements com.lec.sts11_requestparameter.Controller{
	
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	// public void deleteMember(HttpServletRequest request, Model model) { // 순서와 관계없습니다.
	public void deleteMember(Model model, HttpServletRequest request) { // 순서와 관계없습니다.
		
		String id = request.getParameter("id");
		model.addAttribute("id", id);
	
	}
	
	// @RequestMapping(value = "/member/regOk", method = RequestMethod.POST)
	@PostMapping("member/regOk")
	public void regOkmember() {}

	// @RequestMapping(value = "/member/regOk", method = RequestMethod.GET)
	@GetMapping("member/regOk")
	public void regOkmember2() {}
	
	// @RequestMapping(value = "/member/regOk", method = RequestMethod.PUT)
	@PutMapping("member/regOk")
	public void regOkmember3() {}
	
	// @RequestMapping(value = "/member/regOk", method = RequestMethod.DELETE)
	@DeleteMapping("member/regOk")
	public void regOkmember4() {}
	
	@RequestMapping("member/regist")
	public void memberRegist() {}
	
	// POST 와 GET 방식의 두가지 메소드 지원 (동시에 사용 가능)
	@RequestMapping(value = "member/regOk2", //
			method = { RequestMethod.POST, //
					RequestMethod.GET })
	public String regOkmember5() {return "member/regOk";}
	
	@RequestMapping("member/findForm")
	public void memberFindForm() {}

	@RequestMapping("member/findForm2")
	public void memberFindForm2() {}
	
	@RequestMapping("member/findForm3")
	public void memberFindForm3() {}
	
	@RequestMapping("member/findFormException")
	public void memberFindFormException() {}

}
