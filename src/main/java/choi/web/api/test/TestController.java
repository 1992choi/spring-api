package choi.web.api.test;

import choi.web.api.domain.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final OrderServiceImpl orderService;

    @GetMapping("/test/exception")
    public ResponseEntity<ResponseData> testException() {
        int i = 10 / 0;

        return ResponseEntity.ok(new ResponseData("0000", "성공하였습니다."));
    }

    @GetMapping("/test/interface")
    public double testInterface() {
        return orderService.order(2000);
    }

}
