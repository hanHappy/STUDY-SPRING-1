package hello.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

    // 수동으로 bean 등록하기
    // @Bean
    // public MemberService memberService(){
    //     return new MemberService(memberRepository());
    // }

    // @Bean
    // public MemberRepository memberRepository(){
    //     return new MemoryMemberRepository();
    // }
}
