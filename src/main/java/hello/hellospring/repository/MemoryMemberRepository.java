package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository{

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
        // 이렇게 감싸서 반환해주면 클라이언트에서 ~ 할 수 있다
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                                // parameter로 넘어온 name과 같은지 확인
                             .filter(member -> member.getName().equals(name))
                                // 하나라도 찾으면 반환
                                // 만약 없으면 Optional에 의해 null 반환
                             .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // member 전체 반환
    }

    // store를 비우는 method
    public void clearStore(){
        store.clear();
    }
    
}
