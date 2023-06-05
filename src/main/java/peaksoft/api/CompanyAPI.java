package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
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
    @GetMapping
    public List<CompanyResponse> getAllCompanies(){
        return service.getAllCompanies();
    }
    @PostMapping
    public  CompanyResponse saveCompany(@RequestBody CompanyRequest companyRequest){
        return service.saveCompany(companyRequest);
    }
    @PutMapping("{id}")
    public CompanyResponse updateCompany(@PathVariable Long id ,@RequestBody CompanyRequest companyRequest){
        return service.updateCompany(id, companyRequest);
    }
    @GetMapping("{id}")
    public  CompanyResponse getCompanyById(@PathVariable Long id){
        return service.getCompanyById(id);
    }
    @DeleteMapping("{id}")
    public SimpleResponse deleteCompanyById(@PathVariable Long id){
       return service.deleteById(id);
    }
    @PostMapping("{id}")
    public CourseResponse saveCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest){
       return service.saveCourse(courseRequest,id);
    }
}
