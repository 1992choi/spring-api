package choi.web.api.learn.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyBatisController {

    private final MyBatisService myBatisService;

    @GetMapping("/mybatis/cache")
    public void cache() {
        myBatisService.cache();
    }

    @GetMapping("/mybatis/without-cache")
    public void withoutCache() {
        myBatisService.withoutCache();
    }

}
