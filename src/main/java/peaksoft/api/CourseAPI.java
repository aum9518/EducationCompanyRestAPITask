package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoCourse.CourseRequest;
import peaksoft.dto.dtoCourse.CourseResponse;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseAPI {
    private final CourseService service;

    @PermitAll
    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return service.getAllCourses();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("{id}")
    public CourseResponse updateCourseById(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        return service.updateCourseById(id, courseRequest);
    }

    @PermitAll
    @GetMapping("{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteCourseById(@PathVariable Long id) {
        return service.deleteCourseById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("assign/{id}")
    public SimpleResponse assignCourseToInstructor(@PathVariable Long id, @RequestParam Long instructorId) {
        return service.assignCourseToInstructor(id, instructorId);
    }
}
