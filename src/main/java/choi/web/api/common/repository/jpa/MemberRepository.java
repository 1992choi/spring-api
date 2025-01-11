package choi.web.api.common.repository.jpa;


import choi.web.api.common.domain.Member;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @QueryHints({
            @QueryHint(name = org.hibernate.jpa.HibernateHints.HINT_COMMENT, value = "MemberRepository.findByMemberId")
    })
    Member findByMemberId(Long memberId);

}
