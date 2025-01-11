package choi.web.api.common.exception;

import choi.web.api.common.domain.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ResponseData> noHandlerFoundException(NoHandlerFoundException e) {
        log.info("ExceptHandler.noHandlerFoundException = {}", e.getMessage());
        return ResponseEntity.ok(new ResponseData("9999", "URL을 확인해주세요."));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ResponseData> commonException(Exception e) {
        log.info("ExceptHandler.commonException = {}", e.getMessage());
        return ResponseEntity.ok(new ResponseData("9999", "오류가 발생하였습니다."));
    }

}
