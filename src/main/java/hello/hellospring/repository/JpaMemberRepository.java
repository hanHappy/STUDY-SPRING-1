package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 EM을 통해 모든 것이 동작한다
    // gradle에 data-jpa library를 받으면 spring boot가 자동으로 EM 생성해줌
    // JPA를 사용하려면 이 EM을 주입받아야 한다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // PK 기반이 아닌 조회는 JPQL을 작성해야 한다
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                                .setParameter("name", name)
                                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    
}
