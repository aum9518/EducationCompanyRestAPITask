package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PermitAll
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return service.getAllStudents();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return service.saveStudent(studentRequest);
    }
@PermitAll
    @PutMapping("{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        return service.updateStudentById(id, studentRequest);
    }

    @PermitAll
    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteStudentById(@PathVariable Long id) {
        return service.deleteStudentById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("assign/{id}")
    public SimpleResponse assignStudentToGroup(@PathVariable Long id, @RequestParam Long groupId) {
        return service.assignStudentToGroup(id, groupId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("block")
    public List<StudentResponse> getAllBlockOrUnBlockStudents(@RequestParam boolean isBlocked){
        return service.getAllBlockOrUnBlockStudents(isBlocked);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("getBlock/{id}")
    public SimpleResponse blockOrUnBlockStudent(@PathVariable Long id,@RequestParam boolean isBlocked){
        return service.blockOrUnBlockStudent(id,isBlocked);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("filter")
    public List<StudentResponse> getAllStudentByFilter(@RequestParam String word,@RequestParam Long id){
       return service.getAllStudentByFilter(word, id);
    }

}
