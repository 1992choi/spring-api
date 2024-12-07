package choi.web.api.controller;

import choi.web.api.domain.Board;
import choi.web.api.domain.ResponseData;
import choi.web.api.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시물 목록 조회
     */
    @GetMapping("/boards")
    public ResponseEntity<EntityModel<ResponseData>> findBoards() {
        return ResponseEntity.ok().body(
                EntityModel
                        .of(new ResponseData("0000", "성공하였습니다.", boardService.findAllBoard()))
                        .add(linkTo(methodOn(BoardController.class).findBoards()).withSelfRel())
        );
    }

    /**
     * 게시물 등록 (mybatis 키 자동 매핑 테스트)
     * - PK는 자동생성되는 컬럼인데, insert 이후 select 없이도 PK 값을 자동으로 DTO에 매핑하는 기술
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/boards")
    public Board createBoard(@RequestBody Board board) {
        boardService.saveBoard(board);
        return board;
    }

}
