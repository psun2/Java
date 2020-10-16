package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 회원정보 저장

    // 밑의 두 함수를 작동시키면 리턴될 데이터가 없다면
    // null 을 반환하는데 이때 optional 이란 걸로 감싸서
    // 보내주면 null 처리에 용이 합니다.
    Optional<Member> findById(Long id); // 아이디를 기준으로 찾기
    Optional<Member> findByName(String name); // 이름을 기준으로 찾기

    List<Member> findAll(); // 저장된 모든 회원 반환
}
