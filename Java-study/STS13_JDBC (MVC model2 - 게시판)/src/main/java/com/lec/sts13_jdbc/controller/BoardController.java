package com.lec.sts13_jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.sts13_jdbc.beans.BoardDTO;
import com.lec.sts13_jdbc.beans.BoardUID;
import com.lec.sts13_jdbc.command.Command;


@Controller
@EnableAspectJAutoProxy
@RequestMapping("board")
public class BoardController {

	@Autowired @Qualifier("boardControllerValidator") Validator boardValidator;
	@Autowired @Qualifier("boardControllerUidValidator") Validator boardControllerUidValidator;
	@Autowired @Qualifier("listCommand") Command listCommand;
	@Autowired @Qualifier("writeOkCommand") Command writeOkCommand;
	@Autowired @Qualifier("viewCommand") Command viewCommand;
	@Autowired @Qualifier("updateCommand") Command updateCommand;
	@Autowired @Qualifier("updateOkCommand") Command updateOkCommand;
	@Autowired @Qualifier("deleteOkCommand") Command deleteOkCommand;
		
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(boardValidator);
		// binder.addValidators(boardControllerUidValidator);
	}

	@RequestMapping("list.do")
	public void list(Model model) {
		listCommand.excute(model);
	}
	
	@RequestMapping("write.do")
	public String write() {
		return "board/write";
	}

	@PostMapping("writeOk.do")
//	public String writeOk(@ModelAttribute("a") BoardDTO dto) { // 안되네요 ^ㅡ^ 역시 실험정신이 중요
//		writeOkCommand.excute(a);
	public String writeOk(Model model, @Validated BoardDTO dto,  //
			BindingResult result // <-- validator 가 유효성 검사를 한 객체
			) {
		
		// @Validated BoardDTO dto, BindingResult result
		// 위와 같이 나란히 붙어 있어야 모든 동작이 깔끔하게 됩니다.
		// Model 객체를 중간에 끼워서 3시간 날렸습니다.
		
		model.addAttribute("result", dto);
		
		String page = null;
		
		if(result.hasErrors()) {
			page = "board/write";
		}
		else {
			writeOkCommand.excute(model);
			page = "board/writeOk";
		}
		
		return page;
	}
	
	@RequestMapping("view.do")
	// public String view(Model model, @Validated BoardUID uid, BindingResult result) {
	public String view(Model model, long uid) {
		model.addAttribute("uid", uid);
		viewCommand.excute(model);
		return "board/view";
	}
	
	@RequestMapping("update.do")
	// public String update(Model model, @Validated BoardUID uid, BindingResult result) {
		public String update(Model model, long uid) {
		model.addAttribute("uid", uid);
		updateCommand.excute(model);
		return "board/update";
	}
	
	@PostMapping("updateOk.do")
	public String updateOk(Model model, @Validated BoardDTO dto,  //
			BindingResult result // <-- validator 가 유효성 검사를 한 객체
			) {
		model.addAttribute("dto", dto);
		updateOkCommand.excute(model);
		return "board/updateOk";
	}
	
	@RequestMapping("deleteOk.do")
	// public String deleteOk(Model model, @Validated BoardUID uid, BindingResult result) {
	public String deleteOk(Model model, long uid) {
		model.addAttribute("uid", uid);
		deleteOkCommand.excute(model);
		return "board/deleteOk";
	}
	
	
	
	
}
