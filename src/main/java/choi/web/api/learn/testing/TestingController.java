package choi.web.api.learn.testing;

import choi.web.api.common.domain.Member;
import choi.web.api.learn.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestingController {

    private final MemberService memberService;

    /**
     * 회원 조회
     */
    @GetMapping("/testing/members/{memberId}")
    public Member findMember(@PathVariable Long memberId) {
        log.info("memberId = {}", memberId);
        Member member = memberService.findMember(memberId);
        log.info("member = {}", member);

        return member;
    }

}
