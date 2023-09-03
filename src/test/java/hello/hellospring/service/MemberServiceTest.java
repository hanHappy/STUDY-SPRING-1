package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

    MemberService service;

    MemoryMemberRepository repository;

    // MemoryMemberRepository repository = new MemoryMemberRepository();
    // 위와 같이 새로운 Repository Instance를 할당해줄 경우 (new Memory ~)
    // MemberService에서 사용하는 Repository와 MemberServiceTest에서 사용하는 Repository가 서로 다른 인스턴스가 되어버린다
    // 간단하게 알 수 있는 문제로는, Repository의 메모리 데이터가 static이 아니었다면 서로 다른 db가 되면서 문제가 될 것이다
    // 따라서 동일한 Repository로 테스트할 수 있도록 같은 인스턴스를 사용하게 해야 한다
    // 이는 외부에서 Repository를 넣어주도록 DI로써 구현한다
    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void 회원가입(){
        // given : 어떤 데이터를 가지고
        Member member = new Member();
        member.setName("hello");

        // when : 무얼 할 때
        Long savedId = service.join(member);

        // then : 어떤 결과가 기대된다
        Member foundMember = service.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        service.join(member1);

        // try-catch문 대신 사용할 수 있는 코드 2가지
        // (1) Exception class 비교
        assertThrows(IllegalStateException.class, () -> service.join(member2));  // 성공
        // assertThrows(NullPointerException.class, () -> service.join(member2));   실패
        
        // (2) 메시지 비교
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다!!!!");    실패

/*         
        try {
            service.join(member2);
            fail(); // 같은 이름("spring")인데 저장이 되면 실패
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            // assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다!!!!!");   실패
        } 
*/

        // then
    }
}
