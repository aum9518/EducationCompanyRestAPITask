package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCompany.CompanyGetAllResponse;
import peaksoft.dto.dtoCompany.CompanyRequest;
import peaksoft.dto.dtoCompany.CompanyResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.entity.*;
import peaksoft.exception.MyException;
import peaksoft.repository.*;
import peaksoft.service.CompanyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    @Override
    public List<CompanyGetAllResponse> getAllCompanies()  {
        return repository.getAllCompany();
    }

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        try {
            Company company = new Company();
            company.setName(companyRequest.name());
            company.setCountry(companyRequest.country());
            company.setAddress(companyRequest.address());
            if (companyRequest.phoneNumber().contains("+996") || companyRequest.phoneNumber().contains("0")){
                if(companyRequest.phoneNumber().length()==14 || companyRequest.phoneNumber().length()==10){
            company.setPhoneNumber(companyRequest.phoneNumber());
                }else throw new MyException("Phone number length must be 14 or 10 characters");
            }else throw new MyException("Phone number should contain '+996' or '0' ");
            repository.save(company);
            return new CompanyResponse(company.getId(), company.getName(), company.getCountry(), company.getAddress(), company.getPhoneNumber());
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return  null;
    }
    @Override
    public CompanyResponse getCompanyById(Long id) {
        try{
            Company company = repository.findById(id).orElseThrow(() -> new MyException("Company with id: " + id + " is not found"));
            List<Group> allGroups = repository.getAllCompanyCoursesGroups(id);
            List<Student> allStudents = repository.getAllCompaniesCoursesGroupsStudent(id);
            List<String> instructorName = company.getInstructor().stream()
                    .map(Instructor::getFirstName)
                    .collect(Collectors.toList());
            List<String> courseName = company.getCourse().stream()
                    .map(Course::getCourseName)
                    .collect(Collectors.toList());
            List<String> groupName = allGroups.stream()
                            .map(Group::getGroupName)
                                    .toList();
            CompanyResponse companyResponse = new CompanyResponse(company.getId(),
                    company.getName(),
                    company.getCountry(),
                    company.getAddress(),
                    company.getPhoneNumber(),
                    instructorName,
                    courseName,
                    groupName,
                    allStudents.size());

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
                return  SimpleResponse.builder()
                        .status("200")
                        .message("Company with Id deleted")
                        .build();
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
