package com.lec.interceptor.controller;

import com.lec.interceptor.domain.BoardDTO;
import com.lec.interceptor.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("list")
    public void list(Model model) {
        model.addAttribute("result", boardService.getListAll());
    }

    @GetMapping("write")
    public void write(Model model) {
    }

    @PostMapping("writeOk")
    public void writeOk(Model model, BoardDTO dto) {
        model.addAttribute("result", boardService.write(dto));
    }

    @GetMapping("view")
    public void view(Model model, long uid) {
        model.addAttribute("result", boardService.viewByUid(uid));
    }

    @GetMapping("update")
    public void update(Model model, long uid) {
        model.addAttribute("result", boardService.selectByUid(uid));
    }

    @PostMapping("updateOk")
    public void updateOk(Model model, BoardDTO dto) {
        model.addAttribute("result", boardService.update(dto));
    }

    @GetMapping("deleteOk")
    public void deleteOk(Model model, long uid) {
        model.addAttribute("result", boardService.deleteByUid(uid));
    }
}

