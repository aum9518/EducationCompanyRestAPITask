package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCompany.CompanyGetAllResponse;
import peaksoft.dto.dtoCompany.CompanyRequest;
import peaksoft.dto.dtoCompany.CompanyResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyAPI {
    private final CompanyService service;

    @PermitAll
    @GetMapping
    public List<CompanyGetAllResponse> getAllCompanies(){
        return service.getAllCompanies();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public  CompanyResponse saveCompany(@RequestBody CompanyRequest companyRequest){
        return service.saveCompany(companyRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public CompanyResponse updateCompany(@PathVariable Long id ,@RequestBody CompanyRequest companyRequest){
        return service.updateCompany(id, companyRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("{id}")
    public  CompanyResponse getCompanyById(@PathVariable Long id){
        return service.getCompanyById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteCompanyById(@PathVariable Long id){
       return service.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("{id}")
    public CourseResponse saveCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest){
       return service.saveCourse(courseRequest,id);
    }
}
