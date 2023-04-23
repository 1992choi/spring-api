package choi.web.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Null 값인 필드 제외
public record ResponseData(String resultCode, String resultMessage, Object resultData) {

    public ResponseData(String resultCode, String resultMessage) {
        this(resultCode, resultMessage, null);
    }

}