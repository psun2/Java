package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {

    @GetMapping("/start")
    public String start() {
        return "/01-start.spring.io";
    }

    @GetMapping("/spring")
    public String spring() {
        return "/02-spring.io";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "/03-welcomepage";
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf() {
        return "/04-thymeleaf";
    }

    @GetMapping("/build")
    public String build() {
        return "/05-build";
    }

}
