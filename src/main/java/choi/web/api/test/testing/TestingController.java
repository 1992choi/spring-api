package choi.web.api.test.testing;

import choi.web.api.domain.Member;
import choi.web.api.service.MemberService;
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
    @GetMapping("/test/testing/members/{memberId}")
    public Member findMember(@PathVariable Long memberId) {
        log.info("memberId = {}", memberId);
        Member member = memberService.findMember(memberId);
        log.info("member = {}", member);

        return member;
    }

}
