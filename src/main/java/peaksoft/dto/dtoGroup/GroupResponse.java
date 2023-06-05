package peaksoft.dto.dtoGroup;

import lombok.Builder;

@Builder
public record GroupResponse(Long id,String name,String imageLink,String description) {
    public GroupResponse {
    }
}
