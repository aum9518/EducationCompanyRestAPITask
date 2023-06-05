package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoInstructor.InstructorRequest;
import peaksoft.dto.dtoInstructor.InstructorResponse;

import java.util.List;

public interface InstructorService {
    List<InstructorResponse> getAllInstructors();
    InstructorResponse saveInstructor(InstructorRequest instructorRequest);
    InstructorResponse updateInstructorById(Long id,InstructorRequest instructorRequest);
    InstructorResponse getInstructorById(Long id);
    SimpleResponse deleteInstructorById(Long id);
    InstructorResponse assignInstructorToCompany(Long instructorId,Long companyId);
}
