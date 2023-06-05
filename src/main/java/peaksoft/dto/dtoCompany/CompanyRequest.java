package peaksoft.dto.dtoCompany;

public record CompanyRequest(String name,
                             String country,
                             String address,
                             String phoneNumber) {
}
