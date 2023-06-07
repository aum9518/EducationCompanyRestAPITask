package peaksoft.dto.dtoCompany;

import lombok.Builder;

@Builder
public record CompanyGetAllResponse(Long id,
                                    String name,
                                    String country,
                                    String address,
                                    String phoneNumber) {
    public CompanyGetAllResponse {
    }
}
