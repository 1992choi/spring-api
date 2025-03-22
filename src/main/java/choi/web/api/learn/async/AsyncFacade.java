package choi.web.api.learn.async;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AsyncFacade {

    private final AsyncService asyncService;

    private final DummyService dummyService;

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

    @Transactional
    public void syncException() {
        dummyService.saveFistDummy();
        asyncService.syncException();
        dummyService.saveSecondDummy();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void asyncException() {
        dummyService.saveFistDummy();
        asyncService.asyncException();
        dummyService.saveSecondDummy();
    }

}
