package choi.web.api.learn.tx;

import choi.web.api.common.domain.Board;
import choi.web.api.common.domain.Member;
import choi.web.api.common.repository.jpa.MemberRepository;
import choi.web.api.common.repository.mybatis.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionService {

    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

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

            this.case4InnerMethod();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private void case4InnerMethod() throws Exception {
        try {
            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-2")
                            .build()
            );

            int num = 1 / 0;

            memberRepository.save(
                    Member.builder()
                            .name("트랜잭션-3")
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

        this.case5InnerMethod();
    }

    private void case5InnerMethod() {
        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-2")
                        .build()
        );

        int num = 1 / 0;

        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-3")
                        .build()
        );
    }

    /**
     * 실행결과
     * - 모두 롤백되어 저장되는 회원 없음.
     */
    public void case6() {
        this.case6InnerMethod1();
        this.case6InnerMethod2();
    }

    @Transactional
    public void case6InnerMethod1() {
        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-1")
                        .build()
        );

        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-2")
                        .build()
        );
    }

    @Transactional
    public void case6InnerMethod2() {
        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-3")
                        .build()
        );

        int num = 1 / 0;

        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-4")
                        .build()
        );
    }

    /**
     * 실행결과
     * - 모두 롤백되어 저장되는 회원 없음.
     */
    @Transactional
    public void case7() {
        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-1")
                        .build()
        );

        String str = null;
        str.substring(0);

        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-2")
                        .build()
        );
    }

    /**
     * 실행결과
     * - '트랜잭션-1'만 생성.
     *   (NullPointerException는 Unchecked Exception이지만 noRollbackFor 옵션으로 인해 Rollback 무시)
     */
    @Transactional(noRollbackFor = NullPointerException.class)
    public void case8() {
        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-1")
                        .build()
        );

        String str = null;
        str.substring(0);

        memberRepository.save(
                Member.builder()
                        .name("트랜잭션-2")
                        .build()
        );
    }

    /**
        반복 조회 테스트
        - DB에서 조회 후에 값을 변경 후, 변경된 값을 저장하지는 않았지만
          다시 DB에서 조회하면 변경 전 값이 아닌 변경된 값이 조회된다.
          ex) 최초 DB에서 조회한 title         => sample
              setter로 데이터 주입 후 title    => sample_edited_2025-02-08T17:45:39.51259
              다시 DB에서 조회한 title         => sample_edited_2025-02-08T17:45:39.51259


        - TODO. Isolation 속성 때문에 발생하는 것 같았지만, 아닌 것 같기도... mybatis 캐시 설정도 함께 찾아봐야할듯.
          - Isolation와 별개로 mybatis 캐시에 대해서도 작성필요.
            - mybatis 캐시에는 1차 캐시와 2차 캐시가 존재.
              - 2차 캐시는 제어 가능하지만, 1차 캐시는 제어불가. 하지만 우회하는 방법이 존재. flushCache="true"
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public void repeatableTest() {
        Board board = boardRepository.findById(5L);
        log.info("board title = {}", board.getTitle());

        board.setTitle(board.getTitle() + "_edited_" + LocalDateTime.now());
        Board editedBoard = boardRepository.findById(5L);
        log.info("board title = {}", editedBoard.getTitle());
    }

}
