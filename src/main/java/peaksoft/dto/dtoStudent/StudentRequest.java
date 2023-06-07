package peaksoft.dto.dtoStudent;

import lombok.Builder;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;
@Builder
public record StudentRequest(String firstName, String lastName, String phoneNumber, String email, String studyFormat,
                             Gender gender,String password) {
    public StudentRequest {
    }
}
