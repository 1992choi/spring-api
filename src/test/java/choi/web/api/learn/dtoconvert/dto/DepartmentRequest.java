package choi.web.api.learn.dtoconvert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentRequest {

    private Long departmentId;

    private String departmentName;

    private String departmentTel;

    private ManagerRequest manager;

}
