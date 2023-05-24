package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    //JpaRepository<T, ID>에서 기본적으로 다 지원해줌

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
