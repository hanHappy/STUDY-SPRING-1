package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

// 다른 곳에서 가져다 쓸 클래스가 아니므로 public이 아니어도 된다
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각각의 test는 순서에 독립적이어야 한다
    // 즉, 3개의 테스트를 실행한다고 했을 때 첫번째 test의 결과가 이후의 test에 영향을 주어선 안 된다
    // = test는 서로 의존 관계가 없이 설계되어야 한다
    // 따라서 test가 종료될 때마다 저장소를 지운다
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        
        repository.save(member);

        Member result = repository.findById(member.getId()).get();  // Optional에서 꺼낼 때는 get()으로 꺼낸다

        // 저장하려는 객체와 findById로 가져온 member 객체가 같은지 test할 것

        // import static org.assertj.core.api.Assertions.*;
        // static import를 하면 assertThat을 바로 사용할 수 있다
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        // true
        Member result = repository.findByName("spring1").get();

        // false
        // Member result2 = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> list = repository.findAll();
        assertThat(list.size()).isEqualTo(2);

    }

}
