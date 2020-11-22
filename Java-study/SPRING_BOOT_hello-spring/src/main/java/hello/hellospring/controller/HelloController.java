package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // contextPath 가 hello인 url 에 대응합니다.
    public String hello(Model model) { // spring 이 Model 이란 것을 만들어서 넣어 줍니다.
        System.out.println(model.toString()); // {}

        String hello = "hello!!!!!";
        model.addAttribute("data", hello);
        // 마치 JSP Servlet 의 request.setAttribute("data", hello); 와 동일합니다.
        // key 는 data 고 값은 hello!!!!! 입니다.

        // resources 의 templates 와 대응 됩니다. (static은 정적 페이지 이므로 대응할 필요가 없습니다.)
        return "hello"; // hello.html 로 return 합니다.
    }

//    컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다.
//    스프링 부트 템플릿엔진 기본 viewName 매핑
//    resources:templates/ +{ViewName}+ .html

//    참고: spring-boot-devtools 라이브러리를 추가하면, html 파일을 컴파일만 해주면 서버 재시작 없이
//    View 파일 변경이 가능하다.
//    인텔리J 컴파일 방법: 메뉴 build Recompile

}
