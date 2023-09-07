package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

// Service는 비즈니스에 의존적으로 개발한다
// 따라서 용어도 비즈니스적으로 네이밍한다
@Service
public class MemberService {
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /*
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 불가

        // 데이터를 바로 받지 않고,
        // Optional 안에 데이터 객체가 있는 형태
        // null일 가능성이 있으면 Optional을 사용하도록 한다
        // 그런데 아래와 같이 Optional을 바로 반환하는 것은 권장되지 않는다
        // Optional<Member> result = repository.findByName(member.getName());
        validateDuplicateMember(member);

        repository.save(member);
        return member.getId();
    }

    // 아래 중복 검사 로직이 잘 동작하는지 확인하는 가장 좋은 방법은 test case를 활용하는 것이다
    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                // null이 아니라면 아래 로직이 동작
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
    */
    public List<Member> findMembers(){
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return repository.findById(memberId);
    }
}