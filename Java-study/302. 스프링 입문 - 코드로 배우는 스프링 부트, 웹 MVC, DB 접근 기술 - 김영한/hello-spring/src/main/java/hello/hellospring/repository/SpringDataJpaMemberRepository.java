package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from m Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name); // JpaRepository 를 implements 하고 있으면...
    // 구현체를 자동으로 만들어 주고 스프링 bean 에 등록 합니다.
    // 인터페이스가 인터페이스를 상속 받을땐 implements 가 아닌 extends
}
