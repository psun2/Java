package com.lec.sts19_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.sts19_rest.domain.WriteDTO;
import com.lec.sts19_rest.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("result", boardService.list());
	}

	@GetMapping("/write")
	public void write() {
	}

	@PostMapping("/writeOk")
	public void writeOk(WriteDTO dto, Model model) {
		model.addAttribute("result", boardService.write(dto));
	}

	@GetMapping("/view")
	public void view(int uid, Model model) {
		model.addAttribute("result", boardService.viewByUid(uid));
	}

	@GetMapping("/update")
	public void update(int uid, Model model) {
		model.addAttribute("result", boardService.selectByUid(uid));
	}

	@PostMapping("/updateOk")
	public void updateOk(WriteDTO dto, Model model) {
		model.addAttribute("result", boardService.update(dto));
	}

	@RequestMapping("/deleteOk")
	public void deleteOk(int uid, Model model) {
		model.addAttribute("result", boardService.deleteByUid(uid));
	}

	// REST 게시판 작성
	@GetMapping("/rest")
	public void rest() {
	}

} // end controller
