package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// DAO - 느낌이 좀 납니다.
// Repository 에 있는 코드가 진땡인 데 로직을 숨기기 위한 service 란 폴더에서
// 가짜 DAO 를 생성합니다.
@Repository
public class MemoryMemberRepository  implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<Long, Member>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // test 를 위한 클리어
    public void clearStore() {
        store.clear();
    }
}
