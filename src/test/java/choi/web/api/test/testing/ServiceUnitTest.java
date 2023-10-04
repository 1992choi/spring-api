package choi.web.api.test.testing;

import choi.web.api.domain.Member;
import choi.web.api.repository.jpa.MemberRepository;
import choi.web.api.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ServiceUnitTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 조회")
    void findMember() {
        // given
        Long fakeMemberId = 1L;
        Member member = Member.builder()
                .memberId(fakeMemberId)
                .name("CHOI")
                .birth(19920731)
                .build();

        // mocking
        given(memberRepository.findByMemberId(fakeMemberId)).willReturn(member);
        System.out.println("member : " + member);

        // when
        Member findMember = memberService.findMember(fakeMemberId);
        System.out.println("findMember : " + findMember);

        // then
        Assertions.assertSame(member, findMember);
        Assertions.assertEquals(member.getMemberId(), findMember.getMemberId());
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

}