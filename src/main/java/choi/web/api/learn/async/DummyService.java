package choi.web.api.learn.async;

import choi.web.api.common.domain.Dummy;
import choi.web.api.common.repository.jpa.DummyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DummyService {

    private final DummyRepository dummyRepository;

    @Transactional
    public void saveFistDummy() {
        Long maxId = dummyRepository.findTopByOrderByDummyIdDesc().map(Dummy::getDummyId).orElse(0L);
        Dummy dummy = Dummy.builder()
                .dummyId(maxId + 1)
                .data1("fist data")
                .build();

        dummyRepository.save(dummy);
    }

    @Transactional
    public void saveSecondDummy() {
        Long maxId = dummyRepository.findTopByOrderByDummyIdDesc().map(Dummy::getDummyId).orElse(0L);
        Dummy dummy = Dummy.builder()
                .dummyId(maxId + 1)
                .data1("second data")
                .build();

        dummyRepository.save(dummy);
    }

}
