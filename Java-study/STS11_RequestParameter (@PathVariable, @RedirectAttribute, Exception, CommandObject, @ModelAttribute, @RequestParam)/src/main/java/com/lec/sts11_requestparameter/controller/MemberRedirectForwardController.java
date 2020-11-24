package com.lec.sts11_requestparameter.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("member")
public class MemberRedirectForwardController {

	@Autowired
	@Qualifier("logger")
	private Logger logger;

	@RequestMapping("ageCheck")
	public String ageCheck(String age, //
			RedirectAttributes redirectAttributes) {
		// 모든 request.getParameter 은 기본적으로 String 형태입니다.

		logger.info("age-param: {}", age);

		// 마치 모델에 addAttribute와 똑같습니다.
		// RedirectAttribute 객체는 Redirect 되는 주소로
		// param을 넘겨 줍니다.
		redirectAttributes.addAttribute("age", age);

		if (Integer.parseInt(age) < 19)
			return "redirect:member/underAge";
		else
			return "redirect:member/adult";
	}

	@RequestMapping("ageCheck2")
	public String ageCheck2(int age, //
			RedirectAttributes redirectAttributes) {
		// 모든 request.getParameter 은 기본적으로 String 형태입니다.
		// 하지만 자동 형변환이 가능합니다(spring 의 위력)

		logger.info("age-param: {}", age);

		// 마치 모델에 addAttribute와 똑같습니다.
		// RedirectAttribute 객체는 Redirect 되는 주소로
		// param을 넘겨 줍니다.
		redirectAttributes.addAttribute("age", age);

		if (age < 19)
			return "redirect:member/underAge2";
		else
			return "redirect:member/adult2";
	}

	@RequestMapping("underAge")
	public void pageUnder(Model model, @RequestParam("age") String age) {
		// @RequestParam("age") 이름이 바뀔 필요가 없으므로 사용 하지 않아도 됩니다.
		model.addAttribute("age", age);

		logger.info("age-param: {}", age);
		logger.info("model: {}", model);
	}

	@RequestMapping("underAge2")
	public String pageUnder2(Model model, @RequestParam("age") String age) {
		// @RequestParam("age") 이름이 바뀔 필요가 없으므로 사용 하지 않아도 됩니다.
		model.addAttribute("age", age);

		logger.info("age-param: {}", age);
		logger.info("model: {}", model);
		
		return "member/underAge";
	}

	@RequestMapping("adult")
	public void pageAdult(Model model, int age) {
		// @RequestParam("age") 이름이 바뀔 필요가 없으므로 사용 하지 않아도 됩니다.
		model.addAttribute("age", age);

		logger.info("age-param: {}", age);
		logger.info("model: {}", model);
	}
	
	@RequestMapping("adult2")
	public String pageAdult2(Model model, int age) {
		// @RequestParam("age") 이름이 바뀔 필요가 없으므로 사용 하지 않아도 됩니다.
		model.addAttribute("age", age);

		logger.info("age-param: {}", age);
		logger.info("model: {}", model);
		
		return "member/adult";
	}

}
