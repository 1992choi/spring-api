package choi.web.api.learn.async;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AsyncFacade {

    private final AsyncService asyncService;

    @Transactional
    public void sync() {
        for (int i = 0; i < 10; i++) {
            asyncService.sync();
        }
    }

    @Transactional
    public void async() {
        for (int i = 0; i < 10; i++) {
            asyncService.async();
        }
    }

}
