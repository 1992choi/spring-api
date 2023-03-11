package choi.web.api.service;

import choi.web.api.domain.Member;
import choi.web.api.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    /**
     * 회원 전체 조회
     */
    public List<Member> findAllMember() {
        return memberJpaRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Member findMember(Long memberId) {
        return memberJpaRepository.findByMemberId(memberId);
    }

}
