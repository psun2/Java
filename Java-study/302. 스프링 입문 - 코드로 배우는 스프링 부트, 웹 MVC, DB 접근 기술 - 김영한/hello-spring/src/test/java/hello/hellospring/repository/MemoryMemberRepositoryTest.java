package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    // MemberRepository repository = new MemoryMemberRepository();
    // 클리어 메소드는 MemoryMemberRepository 이친구의 것이기때문에
    // 인터 페이스가 아닌 MemoryMemberRepository 로 생성합니다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //모든 Test메서드가 끝날때마다 실행되는 callback 메서드 입니다.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        // ctrl + alt + v
        // Optional<Member> byId = repository.findById(member.getId());
        // 반환 타입이 Optional 이기때문에 한가지 작업을 더 해줍니다.
        // repository.findById(member.getId()).get(); // 사실 get으로 바로 꺼내는 방법이 좋은 방법은 아닙니다
        // 아직 학습이고, test 코드 이기때문 get으로 바로 꺼내 주겠습니다.

        Member result = repository.findById(member.getId()).get();

        // 검증 1. 단순하게 출력
        System.out.println();
        System.out.println("result = " + (result == member));
        System.out.println();

        // 검증 2. Assertions
        Assertions.assertEquals(member, result);
        // assertEquals(x, y);
        // 객체 x와 y가 일치함을 확인합니다.
        // x(예상 값)와 y(실제 값)가 같으면 테스트 통과

        // 검증 3
        // 강의: 자바11 인데 현재 자바 8을 쓰는데 아래 메소드가 보이지 않음
        // Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        // Optional<Member> spring1 = repository.findByName("Spring1");
        // get메소드 이용시 option을 한번 까서 이용 할 수 있습니다.

        Member result = repository.findByName("Spring1").get();

        System.out.println();
        System.out.println("result = " + (result == member1));
        System.out.println();

        Assertions.assertEquals(member1, result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertEquals(2, result.size());
    }
}
