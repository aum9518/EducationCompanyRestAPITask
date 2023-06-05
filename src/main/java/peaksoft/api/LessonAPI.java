package peaksoft.api;

import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<LessonResponse>getAllLessons(){
        return service.getAllLessons();
    }
    @PostMapping
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest,@RequestParam Long courseId){
        return service.saveLesson(lessonRequest,courseId);
    }
    @PutMapping("{id}")
    public LessonResponse updateLesson(@RequestBody LessonRequest lessonRequest,@PathVariable Long id){
       return service.updateLesson(id,lessonRequest);
    }
    @GetMapping("{id}")
    public LessonResponse getLessonById(@PathVariable Long id){
        return service.getLessonById(id);
    }
    @DeleteMapping("{id}")
    public SimpleResponse deleteLessonById(@PathVariable Long id){
        return service.deleteLessonById(id);
    }
}
