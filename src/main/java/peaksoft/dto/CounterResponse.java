package peaksoft.dto;

import lombok.Builder;

@Builder
public record CounterResponse(int quantityOfStudents,int quantityOfInstructors) {
    public CounterResponse {
    }
}
