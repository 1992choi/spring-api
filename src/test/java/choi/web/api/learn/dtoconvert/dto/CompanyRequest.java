package choi.web.api.learn.dtoconvert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyRequest {

    private Long companyId;

    private String companyName;

    private String companyTel;

    private DepartmentRequest department;

}
