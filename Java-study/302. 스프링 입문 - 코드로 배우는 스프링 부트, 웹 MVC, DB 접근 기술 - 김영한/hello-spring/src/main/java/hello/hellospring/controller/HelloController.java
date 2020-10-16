package hello.hellospring.controller;

import hello.hellospring.controller.HelloController.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // API 방식의 response
    @GetMapping("hello-string")
    @ResponseBody // Http 통신은 hrader 부 와 body 부가 나뉘 어 져 있는데,
    // 그 http body 부에 아래의 data를 직접 넣겠다는 의미입니다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // HTML을 리턴 해주는 것이 아니고
        // API를 통해 data만 return 해줄 수 도 있습니다.
    }
    
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // class 를 내보내 줄때 JSON 형식으로 내보내줍니다.
        // 굉장히 신기합니다.
        // ResponseBody 가 붙어 있을때 객체가 오면 JSON 방식으로 객체를 만들어서
        // http 응답에 반환하겠다라는 기본 정택 입니다.

        // spring 은 기본 적으로 객체를 JSON 으로 바꾸어주는
        // Jackson 이라는 라이브러리를 탑제하고 있으며,
        // 객체를 반환할때 Json Converter 로 인하여 JSON 형식으로 반환하게 됩니다.
    }

    static class Hello {
        private String name;

        // 자바 bean 규약
        // getter, setter => property 접근 방식
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
