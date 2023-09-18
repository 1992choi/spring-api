package choi.web.api.test.except;

import choi.web.api.domain.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExceptionController {

    private final ExceptionService exceptionService;

    @GetMapping("/test/exception")
    public ResponseEntity<ResponseData> testException() {
        int i = 10 / 0;
        return ResponseEntity.ok(new ResponseData("0000", "성공하였습니다."));
    }

    // Checked Exception - 예외로 인해 종속 발생
    @GetMapping("/test/checked-exception")
    public ResponseEntity<ResponseData> chekced() throws SQLException {
        exceptionService.findAllWithChecked();
        return ResponseEntity.ok(new ResponseData("0000", "성공하였습니다."));
    }

    // Unchecked Exception - Checked Exception을 Unchecked Exception으로 변경하여 종속 제거
    @GetMapping("/test/unchecked-exception")
    public ResponseEntity<ResponseData> unchekced() {
        exceptionService.findAllWithUnChecked();
        return ResponseEntity.ok(new ResponseData("0000", "성공하였습니다."));
    }

}