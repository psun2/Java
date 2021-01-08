package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    // @RequestMapping("hello")
    public String Hello (Model model){
        model.addAttribute("data", "spring!!");
        return "hello";
    }
}
