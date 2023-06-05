package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoInstructor.InstructorRequest;
import peaksoft.dto.dtoInstructor.InstructorResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.exception.MyException;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository repository;
    private final CompanyRepository companyRepository;
    @Override
    public List<InstructorResponse> getAllInstructors() {
        return repository.getAllInstructor();
    }

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.firstName());
        instructor.setLastName(instructorRequest.lastName());
        instructor.setSpecialization(instructorRequest.specialization());
        repository.save(instructor);
        return InstructorResponse.builder()
                .id(instructor.getId())
                .firstName(instructor.getFirstName())
                .lastName(instructor.getLastName())
                .specialization(instructor.getSpecialization())
                .build();
    }

    @Override
    public InstructorResponse updateInstructorById(Long id, InstructorRequest instructorRequest) {
        try{
            Instructor instructor = repository.findById(id).orElseThrow(() -> new MyException("Instructor with id: " + id + " is not found"));
            for (Course c:instructor.getCourses()) {
                for (Group g : c.getGroups()) {

                }
            }
            instructor.setFirstName(instructorRequest.firstName());
            instructor.setLastName(instructorRequest.lastName());
            instructor.setSpecialization(instructorRequest.specialization());
            repository.save(instructor);
            return InstructorResponse.builder()
                    .id(instructor.getId())
                    .firstName(instructor.getFirstName())
                    .lastName(instructor.getLastName())
                    .specialization(instructor.getSpecialization())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        try{
            Group group = new Group();
            Instructor instructor = repository.findById(id).orElseThrow(() -> new MyException("Instructor with id: " + id + " is not found"));
            for (Group g : repository.getCoursesGroup(1L)) {
                group.setId(g.getId());
                group.setGroupName(g.getGroupName());
                group.setStudents(g.getStudents());
                group.setDescription(g.getDescription());
                group.setCourses(g.getCourses());
                group.setImageLink(g.getImageLink());
            }
           /* List<Group> groups = new ArrayList<>();
            groups.addAll(repository.getCoursesGroup(1L))*/

            return InstructorResponse.builder()
                    .id(instructor.getId())
                    .firstName(instructor.getFirstName())
                    .lastName(instructor.getLastName())
                    .specialization(instructor.getSpecialization())
                    .groupName(group.getGroupName())
                    .studentsQuantity(group.getStudents().size())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse deleteInstructorById(Long id) {
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
                return SimpleResponse.builder()
                        .status("worked")
                        .message("The instructor deleted")
                        .build();
            }else throw new MyException("Instructor with id: "+id +" is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public InstructorResponse assignInstructorToCompany(Long instructorId, Long companyId) {
        try{
            Instructor instructor = repository.findById(instructorId).orElseThrow(() -> new MyException("Instructor with id: " + instructorId + " is not found"));
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new MyException("Comopany with id: " + companyId + " is not found"));

            instructor.getCompanies().add(company);
            company.getInstructor().add(instructor);

            repository.save(instructor);
            companyRepository.save(company);

        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
