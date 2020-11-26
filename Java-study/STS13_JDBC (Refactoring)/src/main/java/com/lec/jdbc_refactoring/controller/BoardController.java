package com.lec.jdbc_refactoring.controller;

import com.lec.jdbc_refactoring.domain.WriteDTO;
import com.lec.jdbc_refactoring.service.BoardService;
import com.lec.jdbc_refactoring.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
   private BoardService boardService;

    @Autowired
    @Qualifier("boardValidator")
    BoardValidator boardValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(boardValidator);
    }

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("result", boardService.readAll());
    }

    @GetMapping("/view")
    public void view(Model model, @Valid WriteDTO dto, BindingResult bindingResult) {
        model.addAttribute("result", boardService.readyAndViewcntIncrease(dto.getUid()));
    }

    @GetMapping("/write")
    public void write() {
    }

    @PostMapping("/writeOk")
    public void writeOk(Model model, @Valid WriteDTO dto, BindingResult bindingResult) {
        model.addAttribute("result", boardService.create(dto));
    }

    @GetMapping("/update")
    public void update(Model model, @Valid WriteDTO dto, BindingResult bindingResult) {
        model.addAttribute("result", boardService.readByUid(dto.getUid()));
    }

    @PostMapping("/updateOk")
    public void updateOk(Model model, @Valid WriteDTO dto, BindingResult bindingResult) {
        model.addAttribute("result", boardService.update(dto));
    }

    @GetMapping("/deleteOk")
    public void deleteOk(Model model, @Valid WriteDTO dto, BindingResult bindingResult) {
        model.addAttribute("result", boardService.delete(dto.getUid()));
    }


}
