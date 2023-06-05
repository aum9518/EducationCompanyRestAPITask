package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.exception.MyException;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.CourseService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository repository;
    private final InstructorRepository instructorRepository;

    @Override
    public List<CourseResponse> getAllCourses() {
        return repository.getAllCourses();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        try{
            Course course = repository.findById(id).orElseThrow(() -> new MyException("Course with id: " + id + " is not found"));
            return CourseResponse.builder()
                    .id(course.getId())
                    .courseName(course.getCourseName())
                    .dateOfStart(course.getDateOfStart())
                    .description(course.getDescription())
                    .build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public CourseResponse updateCourseById(Long id, CourseRequest courseRequest) {
        try{
            Course course = repository.findById(id).orElseThrow(() -> new MyException("Course with id: " + id + " is not found"));
            course.setCourseName(courseRequest.courseName());
            course.setDescription(courseRequest.description());
            repository.save(course);
            return CourseResponse.builder()
                    .id(course.getId())
                    .courseName(course.getCourseName())
                    .description(course.getDescription())
                    .dateOfStart(course.getDateOfStart())
                    .build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse deleteCourseById(Long id) {
      try{
          if (repository.existsById(id)){
              repository.deleteById(id);
              return SimpleResponse.builder()
                      .message("Course with id:"+ id+" is deleted")
                      .status("Done")
                      .build();
          }else throw new MyException("Course with id:" + id + " is not found");
      }catch (MyException e){
          System.out.println(e.getMessage());
      }
        return null;
    }

    @Override
    public SimpleResponse assignCourseToInstructor(Long courseId, Long instructorId) {
        try{
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new MyException("Instructor with id: " + instructorId + " is not found"));
            Course course = repository.findById(courseId).orElseThrow(() -> new MyException("Course with id: " + courseId + " is not found"));

            instructor.getCourses().add(course);
            course.setInstructor(instructor);

            repository.save(course);
            instructorRepository.save(instructor);

            return SimpleResponse.builder()
                    .status("200")
                    .message("Successfully assigned")
                    .build();

        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
