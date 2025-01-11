package choi.web.api.learn.board;

import choi.web.api.common.domain.Board;
import choi.web.api.common.repository.mybatis.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시물 전체 조회
     */
    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    /**
     * 게시물 등록
     */
    public void saveBoard(Board board) {
        boardRepository.saveBoard(board);
        log.info("board = {}", board);
    }

}
