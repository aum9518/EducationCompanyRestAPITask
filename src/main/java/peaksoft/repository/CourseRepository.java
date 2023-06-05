package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.entity.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select new peaksoft.dto.dtoCourse.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description) from Course c order by c.dateOfStart desc")
    List<CourseResponse> getAllCourses();
}