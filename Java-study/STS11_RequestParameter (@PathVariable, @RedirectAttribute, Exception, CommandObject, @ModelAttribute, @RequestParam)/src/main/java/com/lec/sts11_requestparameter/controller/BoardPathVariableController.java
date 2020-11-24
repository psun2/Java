package com.lec.sts11_requestparameter.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardPathVariableController {

	@Autowired
	@Qualifier("logger")
	private Logger logger;

	@RequestMapping("writePath/{name}/{subject}/{content}")
	public String boardPathVariable(Model model, //
			@PathVariable String name, //
			@PathVariable String subject, //
			@PathVariable String content) {

		model.addAttribute("name", name);
		model.addAttribute("subject", subject);
		model.addAttribute("content", content);

		logger.info("param확인, name: {}, subject: {}", name, subject);
		logger.info("param확인, content: {}", content);
		logger.info("Model확인, model: {}", model);

		return "board/writePath";
	}

}
