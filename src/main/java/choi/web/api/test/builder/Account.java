package choi.web.api.test.builder;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {

    private String loginType;

    private String email;

    private String id;

}
