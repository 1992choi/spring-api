package choi.web.api.learn.mybatis;

import choi.web.api.common.domain.Board;
import choi.web.api.common.repository.mybatis.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyBatisService {

    private final BoardRepository boardRepository;

    /**
     * Mybatis 캐시 테스트
     * - 변경한 값을 DB에 저장하지는 않았지만, 캐싱에 의해 변경된 데이터가 조회된다.
     * - 두 번째 조회되는 값을 보면 저장하지 않았지만 _edited 가 붙은 데이터가 조회된다.
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void cache() {
        Board board = boardRepository.findById(5L);
        log.info("board title = {}", board.getTitle());

        board.setTitle(board.getTitle() + "_edited");
        Board editedBoard = boardRepository.findById(5L);
        log.info("board title = {}", editedBoard.getTitle());
    }

    /**
     * Mybatis 캐시 테스트
     * - flushCache="true" 옵션을 사용하면, 캐시를 적용하지 않고 다시 조회해오기 때문에 _edited 가 붙지 않은 데이터가 조회된다.
     * - mybatis 캐시에는 1차 캐시와 2차 캐시가 존재.
     * - 2차 캐시는 제어 가능하지만, 1차 캐시는 제어불가. 하지만 우회하는 방법이 존재. flushCache="true" (2차 캐시를 제어한다고 해도 1차 캐시 때문에 캐싱된 데이터를 가져올 수 밖에 없다. 따라서 1차 캐시 제어를 위해 우회하는 방법을 이용해야한다.)
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void withoutCache() {
        Board board = boardRepository.findByIdWithoutCache(5L);
        log.info("board title = {}", board.getTitle());

        board.setTitle(board.getTitle() + "_edited");
        Board editedBoard = boardRepository.findByIdWithoutCache(5L);
        log.info("board title = {}", editedBoard.getTitle());
    }

}
