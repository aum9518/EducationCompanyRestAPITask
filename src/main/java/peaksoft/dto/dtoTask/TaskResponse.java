package peaksoft.dto.dtoTask;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record TaskResponse(Long id, String taskName, String taskText, LocalDate deadLine) {
    public TaskResponse {
    }
}
