package com.lec.mvc_refactoring.controller.board;

import com.lec.mvc_refactoring.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // controller 의 역할을 함.
@RequestMapping("/board") // board에 맵핑 합니다.
public class BoardController {

    @Autowired
    BoardServiceImpl boardService;

    @GetMapping("list")
    public void list(Model model) {
        model.addAttribute("result", boardService.readAll());
    }
}
