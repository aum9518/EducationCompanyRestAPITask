package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;
import peaksoft.dto.dtoLesson.LessonResponse;
import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select new peaksoft.dto.dtoLesson.LessonResponse(l.id,l.lessonName) from Lesson l")
    List<LessonResponse> getAllLessons();
}