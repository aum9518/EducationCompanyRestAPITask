package peaksoft.service;

import peaksoft.dto.CounterResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoGroup.GroupRequest;
import peaksoft.dto.dtoGroup.GroupResponse;

import java.util.List;

public interface GroupService {
    List<GroupResponse> getAllGroups();
    GroupResponse saveGroup(GroupRequest groupRequest,Long courseId);
    GroupResponse updateGroupById(Long id,GroupRequest groupRequest);
    GroupResponse getGroupById(Long id);
    SimpleResponse deleteGroupById(Long id);
    CounterResponse counter(Long id);

}
