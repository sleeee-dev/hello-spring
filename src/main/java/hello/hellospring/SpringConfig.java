package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //@Service, @Repository 삭제하고 spring bean 을 직접 등록하는 방법
    //memberService에서 memberRepository 사용하도록 등록
    //정상적으로 실행되는것 확인
    //직접 설정파일을 사용시 컴포넌트 스캔 할 때 보다 관리가 쉽다 (이 안에서만 설정을 바꾸면 됨)
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
