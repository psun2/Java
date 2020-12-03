package com.lec.sts10_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 클래스에 @RequestMapping 적용
 * 
 * base-package 이하에 새로운 패키지 작성하고 컨트롤러 클래스 만든 경우 서버를 '재시작' 하는 것이 좋다.
 */
@Controller
@RequestMapping("/board")
public class BoardController {
	
		
	@RequestMapping("/list")   // request -->  /board/list
	public String listBoard() {
		return "board/list_board";
	}
	
	@RequestMapping("/write")
	public String writeBoard() {
		return "board/write_board";
	}

	
	@RequestMapping("/view")  // request --> /board/view/page
	public String viewBoard() {
		return "board/view_board";
	}
	
	@RequestMapping("/update")
	public String updateBoard() {
		return "board/update_board";
	}
	
	@RequestMapping("/delete")
	public String deleteBoard() {
		return "board/delete_board";
	}
	
} // end Controller
