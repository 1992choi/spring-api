package choi.web.api.learn.dbreplica;

import choi.web.api.common.domain.Replica;
import choi.web.api.common.repository.jpa.ReplicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplicaService {

    private final ReplicaRepository replicaRepository;

    @Transactional
    public void save(Replica replica) {
        replicaRepository.save(replica);
    }

    @Transactional(readOnly = true)
    public List<Replica> findAll() {
        return replicaRepository.findAll();
    }

}
