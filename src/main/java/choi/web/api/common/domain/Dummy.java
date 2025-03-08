package choi.web.api.common.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Dummy implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long dummyId;

    private String data1;

    private String data2;

    private String data3;

    @Builder
    private Dummy(Long dummyId, String data1, String data2, String data3) {
        this.dummyId = dummyId;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

}
