package choi.web.api.controller;

import choi.web.api.domain.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/exception")
    public ResponseEntity<ResponseData> testException() {
        int i = 10 / 0;

        return ResponseEntity.ok(new ResponseData("0000", "성공하였습니다."));
    }

}
