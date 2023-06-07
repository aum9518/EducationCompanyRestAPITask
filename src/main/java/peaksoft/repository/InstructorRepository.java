package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;
import peaksoft.dto.dtoInstructor.InstructorResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select new peaksoft.dto.dtoInstructor.InstructorResponse(i.id,i.firstName,i.lastName,i.specialization) from Instructor i")
    List<InstructorResponse> getAllInstructor();

    @Query("select c.groups from Instructor i join Course c where c.id=:courseId ")
    List<Group> getCoursesGroup(Long courseId);

    @Query("select c.groups from Instructor  i join Course c where i.id = :id")
    List<Group> getInstructorsCourses(Long id);

    @Query("select i.companies from Instructor i where i.id=:id")
    List<Company> getAllInstructorCompany(Long id);
}