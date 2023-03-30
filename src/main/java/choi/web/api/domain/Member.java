package choi.web.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    private Integer birth;

    @Builder
    private Member(Long memberId, String name, Integer birth) {
        this.memberId = memberId;
        this.name = name;
        this.birth = birth;
    }

}
