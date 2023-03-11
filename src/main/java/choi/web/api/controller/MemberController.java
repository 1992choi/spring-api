package choi.web.api.controller;

import choi.web.api.domain.ResponseData;
import choi.web.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 목록 조회
     */
    @GetMapping("/members")
    public ResponseEntity findMembers() {
        ResponseData responseData = ResponseData.builder()
                .resultCode("0000")
                .resultMessage("SUCCESS")
                .resultData(memberService.findAllMember())
                .build();

        return ResponseEntity
                .ok()
                .body(responseData);
    }

    /**
     * 회원 조회
     */
    @GetMapping("/members/{memberId}")
    public ResponseEntity findMember(@PathVariable Long memberId) {
        ResponseData responseData = ResponseData.builder()
                .resultCode("0000")
                .resultMessage("SUCCESS")
                .resultData(memberService.findMember(memberId))
                .build();

        return ResponseEntity
                .ok()
                .body(responseData);
    }


}
