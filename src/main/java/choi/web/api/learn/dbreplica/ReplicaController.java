package choi.web.api.learn.dbreplica;

import choi.web.api.common.domain.Replica;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    - DB Master / Slave 구성
      - 복제 기능이 없는 상태에서 (아직 DB 설정을 하지 않은 상태. Master와 Slave를 제대로 선택하는지 확인하기 위해)
        - insert 시 Master DB에 데이터가 들어가는지 확인
        - select 시 Slave 에만 있는 데이터가 조회되는지 확인 (단, 미리 slave DB에만 데이터 적재)

      - save
        - curl -X POST http://localhost:8080/replica -H "Content-Type: application/json" -d '{"value":"master"}'
 */
@RestController
@RequiredArgsConstructor
public class ReplicaController {

    private final ReplicaService replicaService;

    @PostMapping(value = "/replica")
    public void saveReplica(@RequestBody Replica replica) {
        replicaService.save(replica);
    }

    @GetMapping(value = "/replicas")
    public List<Replica> getReplicas() {
        return replicaService.findAll();
    }

}
