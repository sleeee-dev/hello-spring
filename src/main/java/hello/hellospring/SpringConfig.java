package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // JPA 사용을 위한 em 설정
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // jdbc 연결을 위한 설정
//    private DataSource dataSource;
//
//    // 스프링이 dataSource를 자동으로 DI해줌
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //@Service, @Repository 삭제하고 spring bean 을 직접 등록하는 방법
    //memberService에서 memberRepository 사용하도록 등록
    //정상적으로 실행되는것 확인
    //직접 설정파일을 사용시 컴포넌트 스캔 할 때 보다 관리가 쉽다 (이 안에서만 설정을 바꾸면 됨)
    @Bean
    public MemberService memberService(){
//        return new MemberService(memberRepository());
        //spring data jpa 사용을 위한 설정
        return new MemberService(memberRepository);
    }

    //TimeTraceAop 클래스에서 @Component 설정하거나 여기에 직접 bean으로 등록하거나 둘 중 한가지 선택
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

    //spring data jpa 에서는 사용 x
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        //다른 코드의 수정 없이 이부분만 교체 + dataSource DI만 해주면 DB연결이 된다 -> 테스트 코드로 확인하기
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
