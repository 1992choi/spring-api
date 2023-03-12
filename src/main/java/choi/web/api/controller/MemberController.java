package choi.web.api.controller;

import choi.web.api.domain.Member;
import choi.web.api.domain.ResponseData;
import choi.web.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 목록 조회
     */
    @GetMapping("/members")
    public ResponseEntity<EntityModel<ResponseData>> findMembers() {
        List<EntityModel<Member>> members = memberService.findAllMember().stream().map(member -> {
            return EntityModel.of(member,
                    linkTo(methodOn(MemberController.class).findMember(member.getMemberId())).withRel("detail").withType("GET"));
        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(
                EntityModel
                        .of(ResponseData.builder()
                                .resultCode("0000")
                                .resultData(members)
                                .build())
                        .add(linkTo(methodOn(MemberController.class).findMembers()).withSelfRel())
        );
    }

    /**
     * 회원 조회
     */
    @GetMapping("/members/{memberId}")
    public ResponseEntity<EntityModel<ResponseData>> findMember(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(
                EntityModel
                        .of(ResponseData.builder()
                                .resultCode("0000")
                                .resultData(memberService.findMember(memberId))
                                .build())
                        .add(linkTo(methodOn(MemberController.class).findMembers()).withRel("list").withType("GET"))
                        .add(linkTo(methodOn(MemberController.class).findMember(memberId)).withSelfRel().withType("GET"))
                        .add(linkTo(methodOn(MemberController.class).editMember(memberId, new Member())).withRel("update").withType("PUT"))
                        .add(linkTo(methodOn(MemberController.class).deleteMember(memberId)).withRel("delete").withType("DELETE"))
        );
    }

    /**
     * 회원 등록
     */
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody Member member) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .resultCode("0000")
                        .resultData(memberService.saveMember(member))
                        .build()
        );
    }

    /**
     * 회원 수정
     */
    @PutMapping("/members/{memberId}")
    public ResponseEntity editMember(@PathVariable Long memberId, @RequestBody Member member) {
        Member editMember = null;

        try {
            editMember = memberService.editMember(memberId, member);
        } catch (Exception e) {
        }

        return ResponseEntity.ok(
                ResponseData.builder()
                        .resultCode(editMember == null ? "9999" : "0000")
                        .resultData(editMember)
                        .build()
        );
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        boolean isSuccess = true;
        try {
            memberService.deleteMember(memberId);
        } catch (Exception e) {
            isSuccess = false;
        }

        return ResponseEntity.ok(
                ResponseData.builder()
                        .resultCode(isSuccess ? "0000" : "9999")
                        .build()
        );
    }

}
