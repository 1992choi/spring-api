package choi.web.api.learn.member;

import choi.web.api.common.domain.Member;
import choi.web.api.common.repository.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 전체 조회
     */
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Member findMember(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    /**
     * 회원 등록
     */
    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    /**
     * 회원 수정
     */
    @Transactional
    public Member editMember(Long memberId, Member member) throws Exception {
        // Optional로 변경
//        Member findMember = memberRepository.findByMemberId(memberId);
//        if (findMember == null) {
//            throw new Exception("존재하지 않는 회원입니다.");
//        }
        Member findMember = Optional.ofNullable(memberRepository.findByMemberId(memberId)).orElseThrow(() -> new Exception("존재하지 않는 회원입니다."));

        if (member.getName() != null) {
            findMember.setName(member.getName());
        }
        if (member.getAge() != null) {
            findMember.setAge(member.getAge());
        }

        // return memberRepository.save(findMember); // Dirty Checking
        return findMember;
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

}
