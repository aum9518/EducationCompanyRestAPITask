package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoLesson.LessonRequest;
import peaksoft.dto.dtoLesson.LessonResponse;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonAPI {
    private final LessonService service;

    @PermitAll
    @GetMapping
    public List<LessonResponse>getAllLessons(){
        return service.getAllLessons();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest,@RequestParam Long courseId){
        return service.saveLesson(lessonRequest,courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("{id}")
    public LessonResponse updateLesson(@RequestBody LessonRequest lessonRequest,@PathVariable Long id){
       return service.updateLesson(id,lessonRequest);
    }

    @PermitAll
    @GetMapping("{id}")
    public LessonResponse getLessonById(@PathVariable Long id){
        return service.getLessonById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteLessonById(@PathVariable Long id){
        return service.deleteLessonById(id);
    }
}
