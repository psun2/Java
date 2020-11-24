package com.lec.sts10_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller 클래스 추가하면, 반드시 서버 재시작 하세요
//why? 컨테이너에 등록하여야 하기 때문입니다. 컨터이너에 등록하는 작업은 서버가 시작할때 이루어지기 때문입니다.

@Controller // 컨테이너가 생성될때 Bean 으로 등록 됩니다.
//Bean 으로 등록된다는 의미는 Autowired 가용 가능
@RequestMapping("member")
public class MemberController {

	@RequestMapping(value = "/save") // URL => /member + /save
	public String memberSave() { // = /member/save 로 매핑 됩니다.
		return "member/save";
	}

	@RequestMapping(value = "/load") // URL => /member + /load
	public String memberLoad() { // = /member/load
		return "member/load";
	}
	
	// 중복 불가
//	@RequestMapping(value = "/search") // HomeController 에도 똑같은 매핑 존재 Error
//	public String memberSearch() {
//		return "member/search";
//	}
	
	// 핸들러 리턴값이 void 이면
	// mapping 되는 url 문자열에 해당되는 .jsp 파일로 forward 됩니다.
	@RequestMapping(value="remove") // URL => /member/remove
	public void memberRemove() {} // = /member/remove.jsp
	
}
