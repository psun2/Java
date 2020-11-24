package com.lec.sts11_requestparameter.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.sts11_requestparameter.beans.WriteDTO;

@Controller
@RequestMapping("board")
public class BoardController implements com.lec.sts11_requestparameter.Controller {

	@Autowired
	@Qualifier("logger")
	private Logger logger;

	// 커맨드 (Command Object) 사용

	// 게시글 등록 form
	@RequestMapping("write")
	public void boardWrite() {}

	// 기본 방식으로 paramter 받기
	@PostMapping("writeOk")
	public void boardWriteOk(Model model, String name, String subject, String content) {

		WriteDTO dto = new WriteDTO();
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);

		model.addAttribute("dto", dto);
		
		logger.info("param확인, name: {}, subject: {}", name, subject);
		logger.info("param확인, content: {}", content);
		logger.info("Model확인, model: {}", model);
	}

}
