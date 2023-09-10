package choi.web.api.service;

import choi.web.api.domain.Member;
import choi.web.api.repository.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionService {

    private final MemberRepository memberRepository;

    /**
     * 실행결과
     * - 모두 롤백되어 저장되는 회원 없음.
     */
    @Transactional
    public void case1() {
        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-1")
                        .build()
        );

        int num = 1 / 0;

        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-2")
                        .build()
        );
    }

    /**
     * 실행결과
     * - '트랜잭션-1'만 생성
     */
    @Transactional
    public void case2() throws Exception {
        try {
            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-1")
                            .build()
            );

            int num = 1 / 0;

            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-2")
                            .build()
            );
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * 실행결과
     * - 모두 롤백되어 저장되는 회원 없음.
     */
    @Transactional(rollbackFor = Exception.class)
    public void case3() throws Exception {
        try {
            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-1")
                            .build()
            );

            int num = 1 / 0;

            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-2")
                            .build()
            );
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * 실행결과
     * - 모두 롤백되어 저장되는 회원 없음.
     */
    @Transactional(rollbackFor = Exception.class)
    public void case4() throws Exception {
        try {
            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-1")
                            .build()
            );

            case4InnerMethod();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private void case4InnerMethod() throws Exception {
        try {
            int num = 1 / 0;

            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-2")
                            .build()
            );
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * 실행결과
     * - 모두 롤백되어 저장되는 회원 없음.
     */
    @Transactional
    public void case5() {
        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-1")
                        .build()
        );

        case5InnerMethod();
    }

    private void case5InnerMethod() {
        int num = 1 / 0;

        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-2")
                        .build()
        );
    }

}
