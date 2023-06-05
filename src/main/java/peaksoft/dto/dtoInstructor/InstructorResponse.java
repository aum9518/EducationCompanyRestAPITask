package peaksoft.dto.dtoInstructor;

import lombok.Builder;
import lombok.Getter;
import peaksoft.enums.Specialization;
@Builder
public record InstructorResponse(Long id, String firstName, String lastName, Specialization specialization,String groupName, int studentsQuantity) {
    public InstructorResponse {
    }

    public InstructorResponse(Long id, String firstName, String lastName, Specialization specialization) {
        this(id, firstName, lastName, specialization, null, 0);
    }
}
