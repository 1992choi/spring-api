package choi.web.api.common.repository.jpa;

import choi.web.api.common.domain.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<Employee> findAllWithQuerydsl();

    List<Employee> findAllWithQuerydslFetchJoin();

    List<Employee> findAllWithQuerydslWhereTeamName();

}
