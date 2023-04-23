package choi.web.api.repository.mybatis;

import choi.web.api.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<Board> findAll();

}
