package choi.web.api.learn.redis;

import choi.web.api.common.domain.Dummy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    Redis를 사용한 캐시 테스트
    - 캐싱 후 콘솔에 쿼리를 통해 데이터를 가져오는지 확인할 수 있다.
    - testChangeValue()는 DB값 변경 후 재캐싱을 하지 않는다.
        - 테스트방법 : redis 캐시 삭제 > testInit() > testFindDummy() > testFindDummyList() > testChangeValue() > testFindDummy() > testFindDummyList()
    - testChangeValueWithEvict() DB값 변경 후 재캐싱을 한다. (단, 비교를 위해 리스트에 해당하는 dummyList의 값은 재캐싱하지 않음.)
        - 테스트방법 : redis 캐시 삭제 > testInit() > testFindDummy() > testFindDummyList() > testChangeValueWithEvict() > testFindDummy() > testFindDummyList()
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testInit() {
        restTemplate.postForEntity("/redis/init", null, Void.class);
    }

    @Test
    void testChangeValue() {
        restTemplate.put("/redis", null);
    }

    @Test
    void testChangeValueWithEvict() {
        restTemplate.put("/redis-evict", null);
    }

    @Test
    void testFindDummy() {
        // http://localhost:8080/redis/1
        Long dummyId = 1L;
        ResponseEntity<Dummy> response = restTemplate.getForEntity("/redis/{dummyId}", Dummy.class, dummyId);
        log.info("Response = {}", response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testFindDummyList() {
        // http://localhost:8080/redis/by-name/A
        String dummyName = "A";
        ResponseEntity<List> response = restTemplate.getForEntity("/redis/by-name/{dummyName}", List.class, dummyName);
        log.info("Response = {}", response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}