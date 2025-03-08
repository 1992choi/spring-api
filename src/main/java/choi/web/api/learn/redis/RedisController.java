package choi.web.api.learn.redis;

import choi.web.api.common.domain.Dummy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/redis/init")
    public void init() {
        log.info("call init()");
        redisService.init();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/redis")
    public void changeValue() {
        redisService.changeValue();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/redis-evict")
    public void changeValueWithEvict() {
        redisService.changeValueWithEvict();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/redis/{dummyId}")
    public ResponseEntity<Dummy> findDummy(@PathVariable Long dummyId) {
        return ResponseEntity.ok(
                redisService.getDummy(dummyId)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/redis/by-name/{dummyName}")
    public ResponseEntity<List<Dummy>> findDummyList(@PathVariable String dummyName) {
        return ResponseEntity.ok(
                redisService.getDummyList(dummyName)
        );
    }

}