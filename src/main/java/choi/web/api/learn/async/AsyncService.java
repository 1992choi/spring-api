package choi.web.api.learn.async;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    public void sync() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @Async("CustomTaskExecutor")
    public void async() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
