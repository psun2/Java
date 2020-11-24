package com.lec.sts11_requestparameter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("member")
public class MemberModelAndParamAndExceptionController implements com.lec.sts11_requestparameter.Controller{

	@Autowired
	@Qualifier("logger")
	private Logger logger;

	@RequestMapping("find")
	public void memberFind(Model model, String id, String name) {
		// 오버로딩을 허용하지 않아 에러 발생 (메소드 이름 중복 불가)
		
		// get방식에서의 query-string 과 같은 이름이라면 request.getParameter("id") 와 같이
		// 알아서 자동적으로 받아 올 수 있습니다.

		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		logger.info("param확인, id: {}, name: {}", id, name);
		logger.info("Model확인, model: {}", model);

	}

	@RequestMapping("find2")
	public String memberFind2(Model model, @RequestParam("id") String userId, @RequestParam("name") String userName) {
		// 오버로딩을 허용하지 않아 에러 발생 (메소드 이름 중복 불가)
		
		// @RequestParam("id") 으로 변수명이 다른 곳에 binding 시켜줄 수 있습니다.
		// @RequestParam 을 사용한다면 parameter 가 넘어오지 않을때 Exception이 나게 됩니다.

		model.addAttribute("id", userId);
		model.addAttribute("name", userName);
		
		logger.info("param확인, userId: {}, userName: {}", userId, userName);
		logger.info("Model확인, model: {}", model);
		
		return "member/find";

	}
	
	@RequestMapping("find3")
	public String memberFind3(Model model, double id,  String name) {
		// 오버로딩을 허용하지 않아 에러 발생 (메소드 이름 중복 불가)
		
		// 기본적으로 request.getParamter 는 String type 이지만...
		// Spring 은 자동으로 형변환이 가능합니다.
		
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		logger.info("param확인, id: {}, name: {}", id, name);
		logger.info("Model확인, model: {}", model);

		return "member/find";
		
	}
	
	// Exception (예외 처리)
	@ExceptionHandler(NumberFormatException.class)
	// @ExceptionHandler(value = NumberFormatException.class)
	public String handleException(NumberFormatException e) {
		logger.error(e.getMessage());
		return "error";
	}
	

}
