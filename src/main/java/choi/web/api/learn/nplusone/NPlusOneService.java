package choi.web.api.learn.nplusone;

import choi.web.api.common.domain.Employee;
import choi.web.api.common.repository.jpa.EmployeeRepository;
import choi.web.api.common.repository.jpa.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NPlusOneService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

//    @PostConstruct
//    @Transactional
//    public void init() {
//        Team teamA = new Team("team_a");
//        Team teamB = new Team("team_b");
//        Team teamC = new Team("team_c");
//
//        teamRepository.deleteAll();
//        teamRepository.save(teamA);
//        teamRepository.save(teamB);
//        teamRepository.save(teamC);
//
//        employeeRepository.deleteAll();
//        employeeRepository.save(new Employee("emplyee_a", teamA));
//        employeeRepository.save(new Employee("emplyee_b", teamB));
//        employeeRepository.save(new Employee("emplyee_c", teamC));
//        employeeRepository.save(new Employee("emplyee_d", teamA));
//    }

    @Transactional
    public List<Employee> getEmployeesFetchedUsingJpaWithNPlusOneNotOccurs() {
        return employeeRepository.findAll();
    }

    @Transactional
    public List<Employee> getEmployeesFetchedUsingJpaWithNPlusOneOccurs() {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            System.out.println(employee.getTeam().getName());
        }

        return employees;
    }

    @Transactional
    public List<Employee> getEmployeesFetchedUsingJpaFetchJoinWithNPlusOneNotOccurs() {
        List<Employee> employees = employeeRepository.findAllWithEntityGraph();

        for (Employee employee : employees) {
            System.out.println(employee.getTeam().getName());
        }

        return employees;
    }

    @Transactional
    public List<Employee> getEmployeesFetchedUsingQuerydslWithNPlusOneNotOccurs() {
        return employeeRepository.findAllWithQuerydsl();
    }

    @Transactional
    public List<Employee> getEmployeesFetchedUsingQuerydslWithNPlusOneOccurs() {
        List<Employee> employees = employeeRepository.findAllWithQuerydsl();

        for (Employee employee : employees) {
            System.out.println(employee.getTeam().getName());
        }

        return employees;
    }

    @Transactional
    public List<Employee> getEmployeesFetchedUsingQuerydslFetchJoinWithNPlusOneOccurs() {
        List<Employee> employees = employeeRepository.findAllWithQuerydslFetchJoin();

        for (Employee employee : employees) {
            System.out.println(employee.getTeam().getName());
        }

        return employees;
    }

    @Transactional
    public List<Employee> getEmployeesFetchedUsingQuerydslWhereTeamNameWithNPlusOneOccurs() {
        List<Employee> employees = employeeRepository.findAllWithQuerydslWhereTeamName();

        for (Employee employee : employees) {
            System.out.println(employee.getTeam().getName());
        }

        return employees;
    }

}