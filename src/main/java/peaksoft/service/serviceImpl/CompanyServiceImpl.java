package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCompany.CompanyRequest;
import peaksoft.dto.dtoCompany.CompanyResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.dto.dtoGroup.GroupResponse;
import peaksoft.entity.*;
import peaksoft.exception.MyException;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.CompanyService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    @Override
    public List<CompanyResponse> getAllCompanies()  {
        return repository.getAllCompany();
    }

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.name());
        company.setCountry(companyRequest.country());
        company.setAddress(companyRequest.address());
        company.setPhoneNumber(companyRequest.phoneNumber());
        repository.save(company);
        return new CompanyResponse(company.getId(), company.getName(), company.getCountry(),company.getAddress(),company.getPhoneNumber());

    }
    @Override
    public CompanyResponse getCompanyById(Long id) {
        try{
            Company company = repository.findById(id).orElseThrow(() -> new MyException("Company with id: " + id + " is not found"));
            List<Group> allGroups = groupRepository.findAll();
            List<Student> allStudents = studentRepository.findAll();
            List<String> instructorName = company.getInstructor().stream()
                    .map(Instructor::getFirstName)
                    .collect(Collectors.toList());
            List<String> courseName = company.getCourse().stream()
                    .map(Course::getCourseName)
                    .collect(Collectors.toList());
            List<String> groupName = allGroups.stream()
                            .map(Group::getGroupName)
                                    .toList();
            CompanyResponse companyResponse = new CompanyResponse(company.getId(), company.getName(), company.getCountry(),company.getAddress(),company.getPhoneNumber(), instructorName,courseName,groupName, allStudents.size());

           return companyResponse;
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
        try {
            Company company = repository.findById(id).orElseThrow(() -> new MyException("Company with id: \" + id + \" is not found"));
            company.setName(companyRequest.name());
            company.setCountry(companyRequest.country());
            company.setAddress(companyRequest.address());
            company.setPhoneNumber(companyRequest.phoneNumber());
            repository.save(company);
            return new CompanyResponse(company.getId(), company.getName(), company.getCountry(),company.getAddress(),company.getPhoneNumber());
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return new SimpleResponse("Company with id","Id deleted");
            } else throw new MyException("Company with id: " + id + "is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest, Long companyId) {
        try {
            Company company = repository.findById(companyId).orElseThrow(() -> new MyException("Company with id: " + companyId + " is not found"));
            Course course = new Course();
            course.setCourseName(courseRequest.courseName());
            course.setDescription(courseRequest.description());
            course.setDateOfStart(LocalDate.now());
            course.setCompany(company);
            courseRepository.save(course);
            return new CourseResponse(course.getId(),course.getCourseName(),course.getDateOfStart(),course.getDescription());
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
