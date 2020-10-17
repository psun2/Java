package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// @Controller 라는 애노테이션을 보고
// 스프링이 시작 될때 IOC 컨테이너에 MemberController
// 객체를 생성하고 컨테이너에 저장합니다.
// 이런 상황을 스프링 컨테이너 에서 bean 이 관리된다고 표현합니다.
public class MemberController {

    // private final MemberService memberService = new MemberService();
    // new 해서 객체를 생성 할때의 문제점 공유 되어야 할 service 객체가 다른 곳에서
    // 계속 생성 되어, 메모리 낭비 를 하게 됩니다.
    // ex 내가 jsp 에서 DAO 에 접근 할때 서블릿 마다 new ~DAO().기능함수();
    // 와 같이 똑같은 기능을 하는 것을 의미 없이 계속 생성합니다.

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
