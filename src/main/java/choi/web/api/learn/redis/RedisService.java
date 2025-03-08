package choi.web.api.learn.redis;

import choi.web.api.common.domain.Dummy;
import choi.web.api.common.repository.jpa.DummyRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);
    private final DummyRepository dummyRepository;
    private final CacheManager cacheManager;

    @Transactional
    public void init() {
        dummyRepository.deleteAll();

        List<Dummy> dummyList = new ArrayList<>();
        dummyList.add(Dummy.builder().dummyId(1L).data1("A").data2("AA").data3("AAA").build());
        dummyList.add(Dummy.builder().dummyId(2L).data1("A").data2("AA").data3(null).build());
        dummyList.add(Dummy.builder().dummyId(3L).data1("A").data2(null).data3("AAA").build());
        dummyList.add(Dummy.builder().dummyId(4L).data1("A").data2(null).data3(null).build());
        dummyList.add(Dummy.builder().dummyId(5L).data1("B").data2("BB").data3("BBB").build());
        dummyList.add(Dummy.builder().dummyId(6L).data1("B").data2("BB").data3(null).build());
        dummyList.add(Dummy.builder().dummyId(7L).data1("B").data2(null).data3("BBB").build());
        dummyList.add(Dummy.builder().dummyId(8L).data1("B").data2(null).data3(null).build());

        dummyRepository.saveAll(dummyList);
    }

    @Transactional
    public void changeValue() {
        Dummy dummy = dummyRepository.findById(1L).get();
        dummy.setData2("AA_NEW");
        dummy.setData3("AAA_NEW");
    }

//    @CacheEvict(value = {"dummy", "dummyList"}, allEntries = true)
    @CacheEvict(value = "dummy", allEntries = true) // dummy만 갱신되는지 확인
    @Transactional
    public void changeValueWithEvict() {
        Dummy dummy = dummyRepository.findById(1L).get();
        dummy.setData2("AA_NEW");
        dummy.setData3("AAA_NEW");
    }

    @Cacheable(value = "dummy", key = "#dummyId", unless = "#result == null")
    public Dummy getDummy(Long dummyId) {
        return dummyRepository.findById(dummyId).orElse(null);
    }

    @Cacheable(value = "dummyList", key = "#data1", unless = "#result == null")
    public List<Dummy> getDummyList(String data1) {
        return dummyRepository.findAllByData1(data1);
    }

}
