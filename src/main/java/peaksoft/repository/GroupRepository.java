package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.dtoGroup.GroupResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select new peaksoft.dto.dtoGroup.GroupResponse(g.id,g.groupName,g.imageLink,g.description) from Group g")
    List<GroupResponse> getAllGroups();


    @Query("select c.instructor from Group g join Course c where g.id=:groupId")
List<Instructor> getAllGroupsInstructor(Long groupId);


}