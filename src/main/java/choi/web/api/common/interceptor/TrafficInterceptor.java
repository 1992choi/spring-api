package choi.web.api.common.interceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class TrafficInterceptor implements HandlerInterceptor {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Bucket bucket = resolveBucket(request);
        if (bucket.tryConsume(1)) { // 1개 사용 요청
            return true;
        } else {
            log.info("[트래픽 초과] IP = {}", request.getRemoteAddr());
            throw new Exception("반복요청으로 인하여 접근불가");
        }
    }

    // 요청자 IP 추출
    private String getHost(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = httpServletRequest.getRemoteAddr();
        }

        return ip;
    }

    // 버킷 가져오기
    private Bucket resolveBucket(HttpServletRequest httpServletRequest) {
        return cache.computeIfAbsent(getHost(httpServletRequest), this::newBucket);
    }

    // 버킷 생성
    private Bucket newBucket(String apiKey) {
        return Bucket4j.builder()
                // 버킷의 총 크기 = 5, 한 번에 충전되는 토큰 수  = 1, 10초마다 충전
                .addLimit(Bandwidth.classic(20, Refill.intervally(1, Duration.ofSeconds(10))))
                .build();
    }
}
