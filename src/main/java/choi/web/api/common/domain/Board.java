package choi.web.api.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    private Long boardId;

    private String title;

    private String content;

    private String regId;

    private LocalDateTime regDate;

}
