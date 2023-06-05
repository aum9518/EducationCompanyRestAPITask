package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCompany.CompanyRequest;
import peaksoft.dto.dtoCompany.CompanyResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.entity.Company;

import java.util.List;
import java.util.logging.SimpleFormatter;

public interface CompanyService {
    List<CompanyResponse> getAllCompanies();
    CompanyResponse saveCompany(CompanyRequest companyRequest);
    CompanyResponse getCompanyById(Long id);

    CompanyResponse updateCompany(Long id,CompanyRequest companyRequest);
    SimpleResponse deleteById(Long id);
    CourseResponse saveCourse(CourseRequest courseRequest, Long companyId);
}
