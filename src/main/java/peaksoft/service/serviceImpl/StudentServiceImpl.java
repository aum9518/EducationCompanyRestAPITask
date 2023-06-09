package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JWTService;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.MyException;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    @Override
    public List<StudentResponse> getAllStudents() {
        return repository.getAllStudents();
    }

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        User user = new User();
        Student student = new Student();
        student.setFirstName(studentRequest.firstName());
        student.setLastName(studentRequest.lastName());
        student.setPhoneNumber(studentRequest.phoneNumber());
        student.setEmail(studentRequest.email());
        student.setStudyFormat(studentRequest.studyFormat());
        student.setGender(studentRequest.gender());
        student.setIsBlocked(false);


        user.setRole(Role.STUDENT);
        user.setPassword(passwordEncoder.encode(studentRequest.password()));
        user.setEmail(studentRequest.email());

        user.setStudent(student);
        student.setUser(user);

        userRepository.save(user);
        repository.save(student);

        String jwtToken = jwtService.generateToken(user);

        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .phoneNumber(student.getPhoneNumber())
                .email(student.getEmail())
                .studyFormat(student.getStudyFormat())
                .gender(student.getGender())
                .isBlocked(student.getIsBlocked())
                .token(jwtToken)
                .build();
    }

    @Override
    public StudentResponse updateStudentById(Long studentId, StudentRequest studentRequest) {
        try{
            Student student = repository.findById(studentId).orElseThrow(() -> new MyException("Student is not found"));
            student.setFirstName(studentRequest.firstName());
            student.setLastName(studentRequest.lastName());
            student.setPhoneNumber(studentRequest.phoneNumber());
            student.setEmail(studentRequest.email());
            student.setStudyFormat(studentRequest.studyFormat());
            student.setGender(studentRequest.gender());
            repository.save(student);
            return StudentResponse.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .phoneNumber(student.getPhoneNumber())
                    .email(student.getEmail())
                    .studyFormat(student.getStudyFormat())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        try{
            Student student = repository.findById(id).orElseThrow(() -> new MyException("Student is not found"));
            return StudentResponse.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .phoneNumber(student.getPhoneNumber())
                    .email(student.getEmail())
                    .studyFormat(student.getStudyFormat())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse deleteStudentById(Long id) {
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
                return SimpleResponse.builder()
                        .status("200")
                        .message("Successfully deleted...")
                        .build();
            }else throw new MyException("Student is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse assignStudentToGroup(Long studentId, Long groupId) {
        try{
            Student student = repository.findById(studentId).orElseThrow(() -> new MyException("Student is not found..."));
            Group group = groupRepository.findById(groupId).orElseThrow(() -> new MyException("Group is not found..."));
            List<Student> students =new ArrayList<>();
            students.add(student);
            student.setGroup(group);
            group.setStudents(students);
            repository.save(student);
            groupRepository.save(group);
            return SimpleResponse.builder()
                    .status("200")
                    .message("Successfully addigned")
                    .build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse blockOrUnBlockStudent(Long id, Boolean isBlocked) {
        try{
            Student student = repository.findById(id).orElseThrow(() -> new MyException("Student is not found..."));
            student.setIsBlocked(true);
            repository.save(student);
            return SimpleResponse.builder()
                    .status("200")
                    .message("The student is blocked")
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<StudentResponse> getAllBlockOrUnBlockStudents(Boolean isBlocked) {
//      return  repository.getAllByIsBlocked(isBlocked);
        return null;
    }

    @Override
    public List<StudentResponse> getAllStudentByFilter(String word, Long id) {
       return repository.getAllStudentsByFilter(word, id);
    }
}
