package hello.hellospring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.hellospring.domain.Member;

// interface는 다중 상속이 가능하다
// JpaRepository를 상속하면 
// Spring Data JPA가 구현체을 자동으로 만들어 Spring Bean에 등록한다
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    
    // JPQL : select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
