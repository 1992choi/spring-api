package choi.web.api.test.testing;

import choi.web.api.domain.Member;
import choi.web.api.repository.jpa.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryUnitTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 조회")
    void findMembers() {
        // given
        Member member = Member.builder()
                .name("CHOI")
                .birth(19920731)
                .build();

        Member savedMember = memberRepository.save(member);

        // when
        Member findMember = memberRepository.findByMemberId(savedMember.getMemberId());

        // then
        Assertions.assertSame(savedMember, findMember);
        Assertions.assertEquals(savedMember.getMemberId(), findMember.getMemberId());
        Assertions.assertEquals(savedMember.getName(), findMember.getName());
    }

}