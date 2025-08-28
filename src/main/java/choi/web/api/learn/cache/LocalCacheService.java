package choi.web.api.learn.cache;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LocalCacheService {

    private final Map<LocalDate, byte[]> cache = new ConcurrentHashMap<>();

    private final Random random = new Random();

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
        return cache.keySet().stream()
                .sorted()
                .toList();
    }

}
