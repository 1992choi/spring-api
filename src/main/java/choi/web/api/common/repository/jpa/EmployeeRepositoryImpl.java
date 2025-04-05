package choi.web.api.common.repository.jpa;

import choi.web.api.common.domain.Employee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static choi.web.api.common.domain.QEmployee.employee;
import static choi.web.api.common.domain.QTeam.team;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Employee> findAllWithQuerydsl() {
        return queryFactory
                .selectFrom(employee)
                .join(employee.team, team)
                .fetch();
    }

    @Override
    public List<Employee> findAllWithQuerydslFetchJoin() {
        return queryFactory
                .selectFrom(employee)
                .join(employee.team, team).fetchJoin()
                .fetch();
    }

    @Override
    public List<Employee> findAllWithQuerydslWhereTeamName() {
        return queryFactory
                .selectFrom(employee)
                .join(employee.team, team)
                .where(team.name.eq("team_a"))
                .fetch();
    }

}