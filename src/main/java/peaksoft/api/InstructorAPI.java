package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PermitAll
    @GetMapping
    public List<InstructorResponse> getAllInstructors(){
        return service.getAllInstructors();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest){
        return service.saveInstructor(instructorRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public InstructorResponse updateInstructorById(@PathVariable Long id,@RequestBody InstructorRequest instructorRequest){
       return service.updateInstructorById(id, instructorRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("{id}")
    public InstructorResponse getInstructorById(@PathVariable Long id){
        return service.getInstructorById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteInstructorById(@PathVariable Long id){
        return service.deleteInstructorById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("assign/{id}")
    public InstructorResponse assignInstructorToCompany(@PathVariable Long id,@RequestParam Long companyId){
        return service.assignInstructorToCompany(id,companyId);
    }
}
