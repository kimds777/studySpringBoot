package hellow.hellowspring.repository;

import hellow.hellowspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
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
        return store.values() //store Map에 모든 values값들
                .stream() //stream화 해서
                .filter(member -> member.getName().equals(name)) //store에 value인 member의 이름과 파라미터 네임을 비교
                .findAny(); // 같은 값을 있으면 반환 없으면 null.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
