package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // url 이 /hello 로 들어오면
    // 맵핑된 메소드를 호출 합니다.
    @GetMapping("hello")
    public String hello(Model model) {

        // 자료를 넘깁니다.
        model.addAttribute("data", "hello!!");

        return "hello"; // viewName (파일 이름) hello.html
    }
}
