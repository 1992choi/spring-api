package choi.web.api.learn.dtoconvert;

import choi.web.api.learn.dtoconvert.dto.Company;
import choi.web.api.learn.dtoconvert.dto.CompanyRequest;
import choi.web.api.learn.dtoconvert.dto.DepartmentRequest;
import choi.web.api.learn.dtoconvert.dto.ManagerRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class DtoConvertTest {

    private final CompanyMapper mapper = Mappers.getMapper(CompanyMapper.class);

    @Test
    void convert() {
        ManagerRequest managerRequestA = new ManagerRequest(1L, "manager_a", null, 30);
        DepartmentRequest departmentRequestA = new DepartmentRequest(1L, "dept_a", "1234-5678", managerRequestA);
        CompanyRequest companyRequestA = new CompanyRequest(1L, "company_a", "1234-5678", departmentRequestA);
        Company companyA = mapper.toCompany(companyRequestA);
        Assertions.assertThat(companyA.getCompanyName()).isEqualTo("company_a");
        Assertions.assertThat(companyA.getCompanyTel()).isEqualTo("1234-5678");
        Assertions.assertThat(companyA.getDepartment().getDepartmentName()).isEqualTo("dept_a");
        Assertions.assertThat(companyA.getDepartment().getDepartmentTel()).isEqualTo("1234-5678");
        Assertions.assertThat(companyA.getDepartment().getManager().getManagerName()).isEqualTo("manager_a");
        Assertions.assertThat(companyA.getDepartment().getManager().getManagerNickName()).isNull();
        Assertions.assertThat(companyA.getDepartment().getManager().getManagerAge()).isEqualByComparingTo(30);

        ManagerRequest managerRequestB = new ManagerRequest(2L, "manager_b", null, null);
        DepartmentRequest departmentRequestB = new DepartmentRequest(2L, "dept_b", null, managerRequestB);
        CompanyRequest companyRequestB = new CompanyRequest(2L, "company_b", null, departmentRequestB);
        Company companyB = mapper.toCompany(companyRequestB);
        Assertions.assertThat(companyB.getCompanyName()).isEqualTo("company_b");
        Assertions.assertThat(companyB.getCompanyTel()).isNull();
        Assertions.assertThat(companyB.getDepartment().getDepartmentName()).isEqualTo("dept_b");
        Assertions.assertThat(companyB.getDepartment().getDepartmentTel()).isNull();
        Assertions.assertThat(companyB.getDepartment().getManager().getManagerName()).isEqualTo("manager_b");
        Assertions.assertThat(companyB.getDepartment().getManager().getManagerNickName()).isNull();
        Assertions.assertThat(companyB.getDepartment().getManager().getManagerAge()).isNull();
    }

}
