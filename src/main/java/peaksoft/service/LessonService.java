package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoLesson.LessonRequest;
import peaksoft.dto.dtoLesson.LessonResponse;

import java.util.List;

public interface LessonService {
    List<LessonResponse> getAllLessons();
    LessonResponse saveLesson(LessonRequest lessonRequest,Long courseId);
    LessonResponse updateLesson(Long id,LessonRequest lessonRequest);
    LessonResponse getLessonById(Long id);
    SimpleResponse deleteLessonById(Long id);
}
