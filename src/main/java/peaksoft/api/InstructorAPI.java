package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoInstructor.InstructorRequest;
import peaksoft.dto.dtoInstructor.InstructorResponse;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorAPI {
    private final InstructorService service;
    @GetMapping
    public List<InstructorResponse> getAllInstructors(){
        return service.getAllInstructors();
    }
    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest){
        return service.saveInstructor(instructorRequest);
    }
    @PutMapping("{id}")
    public InstructorResponse updateInstructorById(@PathVariable Long id,@RequestBody InstructorRequest instructorRequest){
       return service.updateInstructorById(id, instructorRequest);
    }
    @GetMapping("{id}")
    public InstructorResponse getInstructorById(@PathVariable Long id){
        return service.getInstructorById(id);
    }
    @DeleteMapping("{id}")
    public SimpleResponse deleteInstructorById(@PathVariable Long id){
        return service.deleteInstructorById(id);
    }
    @PutMapping("assign/{id}")
    public InstructorResponse assignInstructorToCompany(@PathVariable Long id,@RequestParam Long companyId){
        return service.assignInstructorToCompany(id,companyId);
    }
}
