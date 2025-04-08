package choi.web.api.learn.dtoconvert;

import choi.web.api.learn.dtoconvert.dto.Company;
import choi.web.api.learn.dtoconvert.dto.CompanyRequest;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {

    Company toCompany(CompanyRequest dto);

}
