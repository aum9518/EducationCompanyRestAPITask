package peaksoft.dto.dtoTask;

import java.time.LocalDate;

public record TaskRequest(String taskName, String taskText, LocalDate deadLine) {
}
