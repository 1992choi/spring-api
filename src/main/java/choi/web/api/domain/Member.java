package choi.web.api.domain;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
