package peaksoft.dto.dtoInstructor;

import lombok.Builder;
import lombok.Getter;
import peaksoft.enums.Specialization;

import java.util.List;

@Builder
public record InstructorResponse(Long id, String firstName, String lastName, Specialization specialization,
                                 List<String> groupName, int studentsQuantity,String token) {
    public InstructorResponse {
    }

    public InstructorResponse(Long id, String firstName, String lastName, Specialization specialization) {
        this(id, firstName, lastName, specialization, null, 0, null);
    }
}
