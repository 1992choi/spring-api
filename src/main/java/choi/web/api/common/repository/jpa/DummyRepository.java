package choi.web.api.common.repository.jpa;


import choi.web.api.common.domain.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DummyRepository extends JpaRepository<Dummy, Long> {

    List<Dummy> findAllByData1(String data1);

}
