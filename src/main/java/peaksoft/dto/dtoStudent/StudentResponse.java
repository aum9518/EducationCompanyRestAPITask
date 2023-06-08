package peaksoft.dto.dtoStudent;

import lombok.Builder;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;
@Builder
public record StudentResponse(Long id, String firstName, String lastName, String phoneNumber, String email, String studyFormat,
                              Gender gender, Boolean isBlocked,String token) {

    public StudentResponse {
    }

    public StudentResponse(Long id, String firstName, String lastName, String phoneNumber, String email, String studyFormat, Gender gender, Boolean isBlocked) {
        this(id, firstName, lastName, phoneNumber, email, studyFormat, gender, isBlocked, null);
    }
}
