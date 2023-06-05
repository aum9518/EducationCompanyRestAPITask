package peaksoft.dto.dtoLesson;

import lombok.Builder;

@Builder
public record LessonResponse(Long id,String lessonName) {
    public LessonResponse {
    }
}
