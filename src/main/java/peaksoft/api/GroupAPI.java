package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CounterResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoGroup.GroupRequest;
import peaksoft.dto.dtoGroup.GroupResponse;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupAPI {
    private final GroupService service;

    @PermitAll
    @GetMapping
     public List<GroupResponse> getAllGroups(){
       return  service.getAllGroups();
     }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
     @PostMapping
     public GroupResponse saveGroup( @RequestBody GroupRequest groupRequest, @RequestParam Long courseId){
        return service.saveGroup(groupRequest, courseId);
     }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
     @PutMapping("{id}")
    public GroupResponse updateGroupById(@PathVariable Long id,@RequestBody GroupRequest groupRequest){
        return service.updateGroupById(id,groupRequest);
     }

     @PermitAll
     @GetMapping("{id}")
    public GroupResponse getGroupById(@PathVariable Long id){
        return service.getGroupById(id);
     }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
     @DeleteMapping("{id}")
    public SimpleResponse deleteGroupById(@PathVariable Long id){
        return service.deleteGroupById(id);
     }

     @PermitAll
     @GetMapping("count/{id}")
     public CounterResponse counting(@PathVariable Long id){
        return service.counter(id);
     }
}
