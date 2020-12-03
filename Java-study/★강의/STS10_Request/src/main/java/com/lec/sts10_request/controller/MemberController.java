package com.lec.sts10_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")  // /member 로 시작하는 request 매핑
public class MemberController {
	
	@RequestMapping(value = "/save")  // URL ->  /member + /save
	public String saveMember() {      //        = /member/save
		return "member/save";
	}
	
	@RequestMapping("/load")
	public String loadMember() {   // URL -> /member/load
		return "member/load";
	}

	// 중복 불가!
//	@RequestMapping("/search")   // URL -> /member/search
//	public String searchMember() {
//		return "member/search";
//	}
	
	// 핸들러 리턴값이 void 이면
	// mapping 되는 url 문자열에 해당하는 .jsp 파일로 forward 된다.
	@RequestMapping("/remove")   // URL -> /member/remove
	public void removeMember() {} //    -> /member/remove.jsp
	
	
}
















