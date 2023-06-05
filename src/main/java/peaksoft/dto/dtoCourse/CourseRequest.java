package peaksoft.dto.dtoCourse;

import lombok.Builder;

@Builder
public record CourseRequest(String courseName, String description) {
}
