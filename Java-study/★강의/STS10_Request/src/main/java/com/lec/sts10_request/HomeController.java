package com.lec.sts10_request;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/common")
	public String cccmmm() {
		return "comn";		
	}
	
	@RequestMapping(value = "/member/search")
	public String searchMember() {
		return "member/search";  // "/WEB-INF/views/" + "member/search" + ".jsp"
	}
	
	@RequestMapping("/member/infoView")
	public String infoMember(Model model) {
		model.addAttribute("mbAge", 30);
		model.addAttribute("mbName", "홍길동");
		return "member/info";
	}
	
	@RequestMapping("/member/find")
	public ModelAndView findMember() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("mbName", "다스베이더");
		mv.addObject("mbDate", "2020/11/24");
		mv.setViewName("member/find");
		
		return mv;
	}
	
	// 확장자 패턴
	@RequestMapping("/member/*.do")
	//@RequestMapping("/member/**")
	public String doMember() {
		return "member/doMember";
	}
	
}
















