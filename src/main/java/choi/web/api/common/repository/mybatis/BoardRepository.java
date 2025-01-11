package choi.web.api.common.repository.mybatis;

import choi.web.api.common.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<Board> findAll();

    void saveBoard(Board board);

}
