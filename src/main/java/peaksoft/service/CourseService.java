package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(Long id);
    CourseResponse updateCourseById(Long id, CourseRequest courseRequest);
    SimpleResponse deleteCourseById(Long id);
    SimpleResponse assignCourseToInstructor(Long courseId,Long instructorId);
}
