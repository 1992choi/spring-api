package choi.web.api.learn.nplusone;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NPlusOneController {

    private final NPlusOneService nPlusOneService;

    /**
     * JPA - N+1 발생안함
     * - team 필드에 실제로 접근하지 않아서 발생하지 않음.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees/jpa/nplusone-not-occurs")
    public void getEmployeesFetchedUsingJpaWithNPlusOneNotOccurs() {
        nPlusOneService.getEmployeesFetchedUsingJpaWithNPlusOneNotOccurs();
    }

    /**
     * JPA - N+1 발생
     * - team 필드에 접근해서 발생.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees/jpa/nplusone-occurs")
    public void getEmployeesFetchedUsingJpaWithNPlusOneOccurs() {
        nPlusOneService.getEmployeesFetchedUsingJpaWithNPlusOneOccurs();
    }

    /**
     * JPA - N+1 발생안함
     * - team 필드에 실제로 접근하였지만, fetch join으로 인해 발생하지 않음.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees/jpa-fetch/nplusone-not-occurs")
    public void getEmployeesFetchedUsingJpaFetchJoinWithNPlusOneNotOccurs() {
        nPlusOneService.getEmployeesFetchedUsingJpaFetchJoinWithNPlusOneNotOccurs();
    }

    /**
     * QueryDSL - N+1 발생안함
     * - team 필드에 실제로 접근하지 않아서 발생하지 않음.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees/querydsl/nplusone-not-occurs")
    public void getEmployeesFetchedUsingQuerydslWithNPlusOneNotOccurs() {
        nPlusOneService.getEmployeesFetchedUsingQuerydslWithNPlusOneNotOccurs();
    }

    /**
     * QueryDSL - N+1 발생
     * - team 필드에 접근해서 발생.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees/querydsl/nplusone-occurs")
    public void getEmployeesFetchedUsingQuerydslWithNPlusOneOccurs() {
        nPlusOneService.getEmployeesFetchedUsingQuerydslWithNPlusOneOccurs();
    }

    /**
     * QueryDSL - N+1 발생
     * - team 필드에 실제로 접근하였지만, fetch join으로 인해 발생하지 않음.
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees/querydsl-fetch/nplusone-occurs")
    public void getEmployeesFetchedUsingQuerydslFetchJoinWithNPlusOneOccurs() {
        nPlusOneService.getEmployeesFetchedUsingQuerydslFetchJoinWithNPlusOneOccurs();
    }

}