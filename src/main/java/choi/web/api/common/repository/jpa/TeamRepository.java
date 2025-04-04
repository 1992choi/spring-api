package choi.web.api.common.repository.jpa;

import choi.web.api.common.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
