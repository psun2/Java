package com.lec.sts11_requestparameter.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.sts11_requestparameter.beans.WriteDTO;

@Controller
@RequestMapping("board")
public class BoardCommandObjectController implements com.lec.sts11_requestparameter.Controller {

	@Autowired
	@Qualifier("logger")
	private Logger logger;

	// 커맨드 객체사용

	@RequestMapping("write2")
	public void boardWrite2() {}

	@RequestMapping("write3")
	public void boardWrite3() {}
	
	@PostMapping("writeOk2")
	public String boardWriteOk2(WriteDTO dto) {
		
		logger.info("WriteDTO dto확인, dto: {}", dto);
		
		return "board/writeOk";
	}
	
	@PostMapping("writeOk3")
	public String boardWriteOk3(@ModelAttribute("DTO") WriteDTO dto) {
		// @ModelAttribute("DTO") : 모델에 담을 것이며 이름은 DTO 로 하겠다
		
		logger.info("@ModelAttribute(\"DTO\") dto확인, dto: {}", dto);
		
		return "board/writeOk";
		
	}

}
