package peaksoft.repository;

import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new peaksoft.dto.dtoStudent.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.gender,s.isBlocked) from Student s")
    List<StudentResponse> getAllStudents();

    @Query("select new peaksoft.dto.dtoStudent.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.gender,s.isBlocked) from Student s where  s.studyFormat = :studyFormat")
    List<StudentResponse> getAllStudentsByFilter(@Param("studyFormat") String studyFormat);

        List<StudentResponse> getAllByIsBlocked(Boolean isBlocked);
}