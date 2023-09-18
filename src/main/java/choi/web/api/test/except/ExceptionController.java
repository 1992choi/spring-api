package choi.web.api.test.except;

import choi.web.api.domain.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExceptionController {

    @GetMapping("/test/exception")
    public ResponseEntity<ResponseData> testException() {
        int i = 10 / 0;
        return ResponseEntity.ok(new ResponseData("0000", "성공하였습니다."));
    }

}