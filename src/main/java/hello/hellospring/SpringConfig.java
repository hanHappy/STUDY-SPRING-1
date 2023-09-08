package hello.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    // 생성자가 하나인 경우 @Autowired 생략 가능
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 수동으로 bean 등록하기
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    // conpenent scan 사용해도 되지만 service, repository와 같이 정형화된 객체가 아니니
    // 직접 spring bean에 등록하여 사용하는 것도 하나의 방법
    // "AOP 사용하는구나" 정도의 느낌
    // @Bean
    // public TimeTraceAop timeTraceAop(){
    //     return new TimeTraceAop();
    // }

    // @Bean
    // public MemberRepository memberRepository(){
    //     return new JpaMemberRepository(em);
    // }
}
