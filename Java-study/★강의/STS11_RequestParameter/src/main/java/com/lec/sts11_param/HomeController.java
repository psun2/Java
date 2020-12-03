package com.lec.sts11_param;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.beans.WriteDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/member/delete", 
				method = RequestMethod.GET)
	public void delMember(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		model.addAttribute("mbId", id);
		
	}
	
//	@RequestMapping(value = "/member/regOk",
//				method = RequestMethod.POST)
	@PostMapping("/member/regOk")
	public void regOkMember() {
		System.out.println("/member/regOk : POST");
	}

//	@RequestMapping(value = "/member/regOk",
//			method = RequestMethod.GET)
	@GetMapping("/member/regOk")
	public void regOkMember2() {
		System.out.println("/member/regOk : GET");
	}
	
//	@RequestMapping(value = "/member/regOk",
//			method = RequestMethod.PUT)
	@PutMapping("/member/regOk")
	public void regOkMember3() {
		System.out.println("/member/regOk : PUT");
	}
	
//	@RequestMapping(value = "/member/regOk",
//			method = RequestMethod.DELETE)
	@DeleteMapping("/member/regOk")
	public void regOkMember4() {
		System.out.println("/member/regOk : DELETE");
	}
	
	@RequestMapping(value = "/member/regist")
	public String registMember() {
		return "member/regist";
	}
	
	@RequestMapping(value = "/member/regOk2",
			method = {RequestMethod.GET, RequestMethod.POST})
	public String regOkMember5() {
		return "member/regOk";
	}
	
	@RequestMapping("/member/find")
	//public void findMember(String id, String name, Model model) {
	//public void findMember(String name, Model model, String id) {
	//public void findMember(String name, Model model, double id) {
	public void findMember(Model model,
			@RequestParam("id") String userid,
			@RequestParam("name") String username
			) {
	
		model.addAttribute("id", userid);
		model.addAttribute("name", username);
	}
	
	//-------------------------------------
	// 커맨드 객체 (Command Object) 사용
	
	// 게시글 등록 form
	@RequestMapping("/board/write")
	public void writeBoard() {}
	
	// 기존 방식으로 
//	@PostMapping("/board/writeOk")
//	public void writeOkBoard(Model model,
//			String name,
//			String subject,
//			String content			
//			) {
//		WriteDTO dto = new WriteDTO();
//		dto.setName(name);
//		dto.setSubject(subject);
//		dto.setContent(content);
//		
//		model.addAttribute("dto", dto);
//	}
	// 커맨드 객체 사용
	@PostMapping("/board/writeOk")
	//public void writeOkBoard(WriteDTO dto) {
	
	public void writeOkBoard(
			@ModelAttribute("DTO") WriteDTO dto) {
	}
	
	
	//--------------------------------
	// Path Variable
	
	@RequestMapping("/board/writePath/{name}/{subject}/{content}")
	public String writePathBoard(Model model,
			@PathVariable String name,
			@PathVariable String subject,
			@PathVariable int content
			) {
		model.addAttribute("name", name);
		model.addAttribute("subject", subject);
		model.addAttribute("content", content);
		
		return "board/writepath";
	}
	
	// redirect:  forward:
	@RequestMapping("/member/ageCheck")
	public String chkAge(int age,
			RedirectAttributes	redirectAttr
			) {
		redirectAttr.addAttribute("age", age);
		
		if(age < 19)
			return "redirect:/member/underAge";
		else
			return "redirect:/member/adult";
	}
	
	@RequestMapping("/member/underAge")
	public void pageUnder(@RequestParam("age") int age, Model model) {
		model.addAttribute("age", age);
	}
	
	@RequestMapping("/member/adult")
	public void pageAdult(@RequestParam("age") int age, Model model) {
		model.addAttribute("age", age);
	}
	
	
}


















