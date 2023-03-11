package choi.web.api.repository;


import choi.web.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(Long memberId);

}
