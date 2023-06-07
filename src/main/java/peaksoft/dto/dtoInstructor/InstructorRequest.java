package peaksoft.dto.dtoInstructor;

import lombok.Builder;
import peaksoft.enums.Specialization;
@Builder
public record InstructorRequest(String firstName, String lastName, Specialization specialization,String password,String email) {
    public InstructorRequest {
    }
}
