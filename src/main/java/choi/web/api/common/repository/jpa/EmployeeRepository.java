package choi.web.api.common.repository.jpa;

import choi.web.api.common.domain.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

    @EntityGraph(attributePaths = "team")
    @Query("SELECT e FROM Employee e")
    List<Employee> findAllWithEntityGraph();

}
