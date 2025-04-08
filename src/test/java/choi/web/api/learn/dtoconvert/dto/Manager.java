package choi.web.api.learn.dtoconvert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Manager {

    private Long managerId;

    private String managerName;

    private String managerNickName;

    private Integer managerAge;

}
