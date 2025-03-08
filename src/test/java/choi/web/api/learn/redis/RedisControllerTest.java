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
    void testFindDummy() {
        Long dummyId = 1L;
        ResponseEntity<Dummy> response = restTemplate.getForEntity("/redis/{dummyId}", Dummy.class, dummyId);
        log.info("Response = {}", response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testFindDummyList() {
        String dummyName = "A";
        ResponseEntity<List> response = restTemplate.getForEntity("/redis/by-name/{dummyName}", List.class, dummyName);
        log.info("Response = {}", response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}