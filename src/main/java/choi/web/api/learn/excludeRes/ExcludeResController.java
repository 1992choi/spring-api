package choi.web.api.learn.excludeRes;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExcludeResController {

    /**
     * 응답값 테스트
     */
    @GetMapping("/exclude-res/exist")
    public ExcludeRes existValue() {
        ExcludeRes excludeRes = new ExcludeRes();
        excludeRes.setRes1("res1");
        excludeRes.setRes2("res2");
        excludeRes.setRes3("res3");
        excludeRes.setRes4("res4");
        excludeRes.setRes5("res5");

        Gson gson = new Gson();
        log.info("gson = {}", gson.toJson(excludeRes)); // gson = {"res1":"res1","res2":"res2","res3":"res3","res4":"res4"}

        return excludeRes;
        /* 응답값
            {
                "res1": "res1",
                "res3": "res3",
                "res4": "res4",
                "res5": "res5"
            }
        */
    }

    /**
     * 응답값 테스트
     */
    @GetMapping("/exclude-res/not-exist")
    public ExcludeRes notExistValue() {
        ExcludeRes excludeRes = new ExcludeRes();

        Gson gson = new Gson();
        log.info("gson = {}", gson.toJson(excludeRes)); // gson = {}

        return excludeRes;
        /* 응답값
            {
                "res1": null,
                "res4": null,
                "res5": null
            }
        */
    }

}
