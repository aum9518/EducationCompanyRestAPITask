package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoStudent.StudentRequest;
import peaksoft.dto.dtoStudent.StudentResponse;
import peaksoft.service.StudentService;

import java.beans.SimpleBeanInfo;
import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentAPI {
    private final StudentService service;

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return service.getAllStudents();
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return service.saveStudent(studentRequest);
    }

    @PutMapping("{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        return service.updateStudentById(id, studentRequest);
    }

    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @DeleteMapping("{id}")
    public SimpleResponse deleteStudentById(@PathVariable Long id) {
        return service.deleteStudentById(id);
    }

    @PutMapping("assign/{id}")
    public SimpleResponse assignStudentToGroup(@PathVariable Long id, @RequestParam Long groupId) {
        return service.assignStudentToGroup(id, groupId);
    }
    @GetMapping("block")
    public List<StudentResponse> getAllBlockOrUnBlockStudents(@RequestParam boolean isBlocked){
        return service.getAllBlockOrUnBlockStudents(isBlocked);
    }
    @PutMapping("getBlock/{id}")
    public SimpleResponse blockOrUnBlockStudent(@PathVariable Long id,@RequestParam boolean isBlocked){
        return service.blockOrUnBlockStudent(id,isBlocked);
    }
    @GetMapping("filter")
    public List<StudentResponse> getAllStudentByFilter(@RequestParam String word){
       return service.getAllStudentByFilter(word);
    }

}
