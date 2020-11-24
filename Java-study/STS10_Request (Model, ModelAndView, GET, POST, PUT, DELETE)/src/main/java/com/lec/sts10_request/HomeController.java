package com.lec.sts10_request;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page. 
 * 애플리케이션 홈 페이지에 대한 요청을 처리합니다.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name. 
	 * 이름을 반환하여 렌더링 할 홈 뷰를 선택하기 만하면됩니다.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/common", method = RequestMethod.GET) // default : RequestMethod.GET (생략가능 합니다.)
	// @GetMapping("/common") // 과 대응 합니다.
	public String cccmmm() {
		logger.info("/common 요청");
		return "comn";
	}

	// @RequestMapping(value = "/member/search", method = RequestMethod.GET)
	// @RequestMapping(value="/member/search") // default : RequestMethod.GET (생략가능
	// 합니다.)
	@GetMapping("/member/search") // 과 대응 합니다.
	public String memberSearch() {
		logger.info("/member/search 요청");
		return "member/search";
	}
	
	// Model
	// 마치 request.setAttribute 와 비슷합니다.
	@RequestMapping("/member/infoView")
	public String memberInfo(Model model) {
		model.addAttribute("mbAge", 30);
		model.addAttribute("mbName", "홍길동");
		logger.info("/member/infoView 요청, model: {}", model);
		// INFO : com.lec.sts10_request.HomeController - /member/infoView 요청, model: {mbAge=30, mbName=홍길동}
		return "member/info";
	}
	
	// ModelAndView
	@RequestMapping("member/find")
	public ModelAndView memberFind() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("mbName", "다스베이더");
		mv.addObject("mbDate", "2020/11/24");
		mv.setViewName("member/find");
		
		logger.info("member/find 요청, ModelAndView: {}", mv);
		// INFO : com.lec.sts10_request.HomeController - member/find 요청, ModelAndView: ModelAndView [view="member/find"; model={mbName=다스베이더, mbDate=2020/11/24}]
		
		return mv;
	}
	
	// 확장자 패턴
	@RequestMapping("/member/*.do")
	// @RequestMapping("/member/**") // member 밑으로 들어오는 모든 요청
	public String doMember() {
		return "member/doMember";
	}

}
