package choi.web.api.test.excludeRes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ExcludeResController {

    /**
     * 응답값 테스트
     */
    @GetMapping("/test/exclude-res/exist")
    public ExcludeRes existValue() {
        ExcludeRes excludeRes = new ExcludeRes();
        excludeRes.setRes1(new ArrayList<>());
        excludeRes.setRes2(new ArrayList<>());
        excludeRes.setRes3(new ArrayList<>());

        return excludeRes;
        /* 응답값
            {
                "res2": [],
                "res3": []
            }
        */
    }

    /**
     * 응답값 테스트
     */
    @GetMapping("/test/exclude-res/not-exist")
    public ExcludeRes notExistValue() {
        return new ExcludeRes();
        /* 응답값
            {
                "res2": null
            }
        */
    }

}
