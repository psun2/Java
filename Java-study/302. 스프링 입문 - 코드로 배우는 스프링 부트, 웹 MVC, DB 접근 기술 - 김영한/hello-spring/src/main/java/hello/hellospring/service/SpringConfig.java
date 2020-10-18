package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이전 시간 @AutoWired 와
// @Repository, @Service 로 자동으로 spring bean 에 등록하여 썻다면,

// 이번시간에는 Spring Bean 에 직접 등록 하는 방법 을 알아 보도록 하겠습니다.

@Configuration
public class SpringConfig {

    @Bean // 스프링 IOC 컨테이너에 등록 합니다.
    // 장점 코드 수정이 없고 바꿔 끼울 객체의 명만 변경 해주면 됩니다.

    // ex) @Repository 가서 ... 기존의 Repository 애노테이션 을 지우고
    // 새로 끼워 넣을 객체를 @Repository 하고 @Service 또한...

    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링 IOC 컨테이너에 등록 합니다.
    // 장점 코드 수정이 없고 바꿔 끼울 객체의 명만 변경 해주면 됩니다.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
