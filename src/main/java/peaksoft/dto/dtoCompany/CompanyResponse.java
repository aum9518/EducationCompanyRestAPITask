package peaksoft.dto.dtoCompany;

import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;
import java.util.Map;


public record CompanyResponse(Long id,
                              String name,
                              String country,
                              String address,
                              String phoneNumber,
                             List<String> instructorName,
                              List<String> courseName,
                              List<String> groupName,
                              int quantityOfStudents) {
    public CompanyResponse {
    }

    public CompanyResponse(Long id, String name, String country, String address, String phoneNumber) {
        this(id, name, country, address, phoneNumber, null, null, null, 0);
    }
}

