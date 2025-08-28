package choi.web.api.learn.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/*
    - 로컬 캐시 VS 카페인 캐시
      - 로컬 캐시는 구현은 단순하나 자동 만료 정책이 없어 메모리 누수 위험 존재

    - 테스트 방법
      - OOME 발생하도록 실행환경을 낮춘 후 로컬과 카페인 캐시를 호출한다.
        - Run -> Edit Configurations -> VM Options : -Xms64m -Xmx64m 추가 (프로젝트 상황에 맞게 변경 필요)
        - 로컬 캐시는 만료정책이 없을 뿐더라, 저장할 수 있는 최대 사이즈가 없기 때문에 OOME가 발생할 확률이 크다.
    
    - 로컬
      - curl -X PUT http://localhost:8080/cache/local
      - curl -X GET http://localhost:8080/cache/local

    - 카페인
      - curl -X PUT http://localhost:8080/cache/caffeine
      - curl -X GET http://localhost:8080/cache/caffeine
 */
@RequiredArgsConstructor
@RestController
public class CacheController {

    private final LocalCacheService localCacheService;

    private final CaffeineCacheService caffeineCacheService;

    @PutMapping(value = "/cache/local")
    public void putLocalCache() {
        localCacheService.putRate();
    }

    @GetMapping(value = "/cache/local")
    public List<LocalDate> getLocalCache() {
        return localCacheService.getRates();
    }

    @PutMapping(value = "/cache/caffeine")
    public void putCaffeineCache() {
        caffeineCacheService.putRate();
    }

    @GetMapping(value = "/cache/caffeine")
    public List<LocalDate> getCaffeineCache() {
        return caffeineCacheService.getRates();
    }

}
