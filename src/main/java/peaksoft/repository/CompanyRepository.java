package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoCompany.CompanyResponse;
import peaksoft.entity.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select new peaksoft.dto.dtoCompany.CompanyResponse(c.id,c.name,c.address,c.country,c.phoneNumber) from Company c")
    List<CompanyResponse> getAllCompany();
}