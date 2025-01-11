package choi.web.api.learn.resilience;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResilienceService {

    /*
        @Retry
        - 실패 시 retry.
        - 단, 설정되어 있는 Exception 시에만 retry.
     */
    // @Retry(name = "circuitbreaker", fallbackMethod = "fallback")

    /*
        @CircuitBreaker
        - 설정된 Exception이 발생하면 회로가 차단된다.
        - 회로가 차단(=OPEN 상태로 변경된 상태)되면 정상 호출도 요청이 들어가지 않는다.
          - Ex) retry를 계속 실행해서 회로를 차단한 후 success 요청해도 정상으로 처리되지 않음.
                다시 HALF_OPEN 또는 CLOSE 상태로 변한 상태에서 success 요청하면 정상 처리됨.
          - Ex) retry-ignore를 계속 실행하면 회로가 차단되지 않으므로 success를 요청하면 정상 처리됨.
     */
    @CircuitBreaker(name = "circuitbreaker", fallbackMethod = "fallback")
    public void circuitbreaker(String type) {
        if ("retry".equals(type)) {
            log.info("Occur RetryableException!");
            throw new RetryableException("RetryableException.");
        } else if ("retry-ignore".equals(type)) {
            log.info("Occur RetryableIgnoreException!");
            throw new RetryableIgnoreException("RetryableIgnoreException.");
        } else if ("success".equals(type)) {
            log.info("success");
        }
    }

    public void fallback(Throwable throwable) {
        log.info("call fallback()");
    }

}
