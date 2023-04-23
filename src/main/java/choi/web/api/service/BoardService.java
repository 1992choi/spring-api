package choi.web.api.service;

import choi.web.api.domain.Board;
import choi.web.api.repository.mybatis.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
