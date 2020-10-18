package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new") // urlMapping
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // Param Mapping
    public String create (MemberForm form) {
        // 들여오는 MemberForm 에 있는 필드 명은 html 의 name 명이랑 같아야
        // spring 이 자동적으로 이름에 맞는 필드를 매칭 시켜 set~ 을 시켜줍니다.
        // 그럼 form 이라는 우리가 흔히 아는 데이터가 setting 된 DTO 를 만들어 주고

        // 우리는 이제 form 에서 값을 꺼내어 유효성 검사를 한뒤
        
        Member member = new Member();
        member.setName(form.getName());

        // Member 라는 객체를 생성 하여 각 필드에 입력 해줍니다.

        // 굳이 똑같은 파일을 가르는 이유가 있을까... ?
        // 이 예제에서는 id 라는 필드를 primary key  로 사용 하기 위하여
        // id 값은 받고 있지 않아서 ... ? (아직은 잘 모르겠다...)

        memberService.join(member);
        // 모든 유효성이 통과 되었으므로 db에 저장을 한뒤

        return "redirect:/"; // 홈화면(root) 로 돌아가기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
