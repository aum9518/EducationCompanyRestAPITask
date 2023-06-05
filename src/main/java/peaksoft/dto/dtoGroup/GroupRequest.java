package peaksoft.dto.dtoGroup;

import lombok.Builder;

@Builder
public record GroupRequest(String name,String imageLink,String description) {
}
