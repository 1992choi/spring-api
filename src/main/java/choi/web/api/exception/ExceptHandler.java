package choi.web.api.exception;

import choi.web.api.domain.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ResponseData> commonException(Exception e) {
        log.info("ExceptHandler.commonException = {}", e.getMessage());
        return ResponseEntity.ok(
                ResponseData.builder()
                        .resultCode("9999")
                        .build()
        );
    }

}
