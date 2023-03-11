package choi.web.api.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseData {

    String resultCode;

    String resultMessage;

    Object resultData;

}
