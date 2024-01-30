package choi.web.api.test.excludeRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcludeRes {

    @JsonIgnore // 응답값에 포함하지 않음
    List<String> res1;

    @Transient // 영속 대상에서 제외할 때 사용하는 어노테이션
    List<String> res2;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 값이 NULL일 때만 응답값에 포함하지 않음
    List<String> res3;

}
