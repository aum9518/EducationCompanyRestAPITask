package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoLesson.LessonRequest;
import peaksoft.dto.dtoLesson.LessonResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.exception.MyException;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository repository;
    private final CourseRepository courseRepository;

    @Override
    public List<LessonResponse> getAllLessons() {
        return repository.getAllLessons();
    }

    @Override
    public LessonResponse saveLesson(LessonRequest lessonRequest, Long courseId) {
        try{
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new MyException("Course is not found"));
            Lesson lesson = new Lesson();
            lesson.setCourse(course);
            lesson.setLessonName(lessonRequest.lessonName());
            repository.save(lesson);
            return LessonResponse.builder()
                    .id(lesson.getId())
                    .lessonName(lesson.getLessonName())
                    .build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public LessonResponse updateLesson(Long id, LessonRequest lessonRequest) {
        try{
            Lesson lesson = repository.findById(id).orElseThrow(() -> new MyException("Lesson is not found"));
            lesson.setLessonName(lessonRequest.lessonName());
            repository.save(lesson);
            return LessonResponse.builder()
                    .id(lesson.getId())
                    .lessonName(lesson.getLessonName())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        try{
            Lesson lesson = repository.findById(id).orElseThrow(() -> new MyException("Lesson is not found"));
            return LessonResponse.builder()
                    .id(lesson.getId())
                    .lessonName(lesson.getLessonName())
                    .build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse deleteLessonById(Long id) {
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
                return SimpleResponse.builder()
                        .status("200")
                        .message("successfully deleted")
                        .build();
            }else  throw new MyException("Lesson is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
