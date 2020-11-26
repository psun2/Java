package com.lec.sts13_jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lec.sts13_jdbc.domain.WriteDTO;
import com.lec.sts13_jdbc.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("list.do")
	public String list(Model model) {
		model.addAttribute("list", boardService.list());
		// new BListCommand().excute(model);
		return "board/list";
	}

	@RequestMapping("write.do")
	public String write() {
		return "board/write";
	}

	@PostMapping("writeOk.do")
	public String writeOk(WriteDTO dto, Model model) {
		model.addAttribute("result", boardService.write(dto));
		// new BWriteComment().excute(model);
		return "board/writeOk";
	}

	@RequestMapping("view.do")
	public String view(Model model, long uid) {
		model.addAttribute("result", boardService.viewByUid(uid));
		// new BViewCommand().excute(model);
		return "board/view";
	}

	@RequestMapping("update.do")
	public String update(Model model, long uid) {
		model.addAttribute("result", boardService.selectByUid(uid));
		// new BUpdateCommand().excute(model);
		return "board/update";
	}

	@PostMapping("updateOk.do")
	public String updateOk(Model model, WriteDTO dto) {
		model.addAttribute("result", boardService.update(dto));
		// new BUpdateOkCommand().excute(model);
		return "board/updateOk";
	}

	@RequestMapping("deleteOk.do")
	public String delete(Model model, long uid) {
		model.addAttribute("result", boardService.deleteByUid(uid));
		// new BDeleteCommand().excute(model);
		return "board/deleteOk";
	}

} // end controller
