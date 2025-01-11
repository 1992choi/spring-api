package choi.web.api.common.repository.jpa;


import choi.web.api.common.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
