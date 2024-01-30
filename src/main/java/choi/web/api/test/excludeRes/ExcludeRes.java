package choi.web.api.test.excludeRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExcludeRes {

    String res1;

    @JsonIgnore // 응답값에 포함하지 않음
    String res2;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 값이 NULL일 때만 응답값에 포함하지 않음
    String res3;

    @Transient // 영속 대상에서 제외할 때 사용하는 어노테이션
    String res4;

    transient String res5; // 직렬화 제외

}
