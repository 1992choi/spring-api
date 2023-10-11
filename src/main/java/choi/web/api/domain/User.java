package choi.web.api.domain;

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
     */

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

}
