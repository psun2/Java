package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    // 주소 맵핑
    // ex) localhost:8080/hello
    public String hello(Model model) {
        model.addAttribute("data", "Spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 주소 맵핑
    // ex) localhost:8080/hello-mvc
    // public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
    // @RequestParam("name") get방식
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // required default 가 true 이며 default 즉 true 상태 일때는 무조건
        // parameter을 넘겨야합니다.
        model.addAttribute("name", name);
        return "hello-template"; // templates의 실행할 파일 명
    }
}
