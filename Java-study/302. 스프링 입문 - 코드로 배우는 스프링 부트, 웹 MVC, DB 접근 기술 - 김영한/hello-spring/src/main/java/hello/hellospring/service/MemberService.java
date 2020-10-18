package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.Optional;

//@Service
// Repository 에 있는 코드가 진땡인 데 로직을 숨기기 위한 service 란 폴더에서
// 가짜 DAO 를 생성합니다.
public class MemberService { // 애노테이션이 붙기 전까지
    // MemberService 는 순수한 자바 클래스 입니다.
    // 스프링이 MemberService를 알 수 가 없습니다.

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // Optional<Member> result = memoryMemberRepository.findByName(member.getName());
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent: 이미 존재한다면...
                    // result.ifPresent(m -> { // ifPresent: 이미 존재한다면...
                    // .ifPresent: result 가 null 이 아닐때 해당 로직이 호출되어 실행됩니다.
                    // if(result != null) 이와 같은 식이랑 같은 문법 입니다.
                    throw new IllegalStateException("이미 존재 하는 회원입니다.");
                });
//        Stream처럼 사용하기
//        Optional을 제대로 사용하려면,
//        Optional을 최대 1개의 원소를 가지고 있는 특별한 Stream이라고 생각하시면 좋습니다.
//        Optional 클래스와 Stream 클래스 간에 직접적인 구현이나 상속관계는 없지만
//        사용 방법이나 기본 사상이 매우 유사하기 때문입니다.
//        Stream 클래스가 가지고 있는 map()이나 flatMap(), filter()와 같은 메소드를
//        Optional도 가지고 있습니다.

    }

    /*
     * 전체 회원 조회
     * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
