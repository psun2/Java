package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // test가 끝나면 rollback 할 수 있습니다.
//@SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.
//@Transactional : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스
//        트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Commit
    void 회원가입() { // 테스트 코드를 작성할때 제약이 없다면
        // 직관적으로 한글을 입력 하는 방법 도 있습니다.

        // given-when-then 주석 문법
        // given - 무엇인가가 주어 졋는데
        Member member = new Member();
        member.setName("spring");

        // when - 이것을 실행 했을때
        long saveId = memberService.join(member);

        // then - 결과가 이게 나와야 돼
        Member findMember = memberService.findOne(saveId).get();// 예제이므로 단 순히 하기 위해
        //get 으로 그냥 받습니다.
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        org.assertj.core.api.Assertions.assertThat(e).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail("이미 존재하는 회원입니다.");
//        } catch (IllegalStateException e) {
//            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }
}
