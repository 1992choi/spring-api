package choi.web.api.controller;

import choi.web.api.domain.ResponseData;
import choi.web.api.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
