package peaksoft.api;

import lombok.RequiredArgsConstructor;
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

    @GetMapping
     public List<GroupResponse> getAllGroups(){
       return  service.getAllGroups();
     }

     @PostMapping
     public GroupResponse saveGroup( @RequestBody GroupRequest groupRequest, @RequestParam Long courseId){
        return service.saveGroup(groupRequest, courseId);
     }

     @PutMapping("{id}")
    public GroupResponse updateGroupById(@PathVariable Long id,@RequestBody GroupRequest groupRequest){
        return service.updateGroupById(id,groupRequest);
     }

     @GetMapping("{id}")
    public GroupResponse getGroupById(@PathVariable Long id){
        return service.getGroupById(id);
     }

     @DeleteMapping("{id}")
    public SimpleResponse deleteGroupById(@PathVariable Long id){
        return service.deleteGroupById(id);
     }
     @GetMapping("count/{id}")
     public CounterResponse counting(@PathVariable Long id){
        return service.counter(id);
     }
}
