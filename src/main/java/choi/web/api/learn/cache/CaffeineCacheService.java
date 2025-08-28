package choi.web.api.learn.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CaffeineCacheService {

    private final Cache<LocalDate, byte[]> cache;

    private final Random random = new Random();

    public CaffeineCacheService() {
        this.cache = Caffeine.newBuilder()
                .maximumSize(10) // 최대 유지 건수
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build();
    }

    public void putRate() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusMonths(1);

        for (LocalDate date = startDate; !date.isAfter(today); date = date.plusDays(1)) {
            byte[] data = new byte[3_000_000]; // OOME 발생을 위하여 의도적으로 큰 사이즈로 설정
            random.nextBytes(data);
            cache.put(date, data);
        }
    }

    public List<LocalDate> getRates() {
        return cache.asMap().keySet().stream()
                .sorted()
                .toList();
    }

}
