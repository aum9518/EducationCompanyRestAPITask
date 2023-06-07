package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoCompany.CompanyGetAllResponse;
import peaksoft.dto.dtoCompany.CompanyResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.entity.Student;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select new peaksoft.dto.dtoCompany.CompanyGetAllResponse(c.id,c.name,c.address,c.country,c.phoneNumber) from Company c")
    List<CompanyGetAllResponse> getAllCompany();

 @Query("select c.groups from Company company join company.course c where company.id=:id")
    List<Group> getAllCompanyCoursesGroups(Long id);
 @Query("select g.students from Company c join c.course co join co.groups g where c.id=:id")
 List<Student> getAllCompaniesCoursesGroupsStudent(Long id);
}