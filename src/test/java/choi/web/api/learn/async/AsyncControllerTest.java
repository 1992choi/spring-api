package choi.web.api.learn.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AsyncControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testSync() {
        /*
            300ms sleep을 10번 수행 = 동기의 경우 약 3000ms
         */
        ResponseEntity<String> response = restTemplate.getForEntity("/sync", String.class);
        log.info("Execution time = {}", response.getBody());
    }

    @Test
    void testAsync() {
        /*
            300ms sleep을 10번 수행 = 비동기의 경우 약 30ms
         */
        ResponseEntity<String> response = restTemplate.getForEntity("/async", String.class);
        log.info("Execution time = {}", response.getBody());
    }

    @Test
    void testSyncException() {
        /*
            예외 발생하면, 롤백
         */
        ResponseEntity<String> response = restTemplate.getForEntity("/sync/exception", String.class);
    }

    @Test
    void testAsyncException() {
        /*
            예외 발생해도 롤백되지 않음. (@Async 어노테이션 특성)
            - 비동기 작업은 별도의 스레드에서 실행되기 때문
         */
        ResponseEntity<String> response = restTemplate.getForEntity("/async/exception", String.class);
    }

}