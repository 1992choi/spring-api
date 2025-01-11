package choi.web.api.learn.builder;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {

    private String loginType;

    private String email;

    private String id;

}
