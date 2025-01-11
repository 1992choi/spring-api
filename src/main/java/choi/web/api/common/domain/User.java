package choi.web.api.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "TB_USER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /*
        - 전략 변경 전
           : 컬럼명이 대문자 Snake case인 경우,
             @Column 어노테이션을 통해 지정해줘야한다.

        - 전략 변경 전
           : Naming 전략을 명시한 설정 파일을 작성 후,
             아래와 같이 설정을 등록하면 @Column 어노테이션을 설정하지 않아도 대문자 Snake case로 조회 가능
             [ properties.put("hibernate.physical_naming_strategy", "choi.web.api.config.JpaNamingStrategyConfig"); ]
     */

    @Id
    // @Column(name = "USER_ID")
    private Long userId;

    // @Column(name = "USER_NAME")
    private String userName;

}
