package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//test class 쉽게 만드는 방법
//ctrl+shift+T : create new test
//@Service //component 직접 등록하기
public class MemberService {

    //MemberService의 MemoryMemberRepository와 MemberServiceTestdml MemoryMemberRepository가 각각 다른 객체이므로
    //같은 Repository로 쓰기 위해 수정하기
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // => new로 생성 X 외부에서 생성자 만들기 : DI
    //DI는 생성자 주입을 주로 사용

    private final MemberRepository memberRepository;

//    @Autowired //component 직접 등록하기
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원은 가입X
        validateDuplicateMember(member); //중복 회원 검증 //ctrl+alt+shift+T 리팩토링 -> extract method로 함수로 만들어내기

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
