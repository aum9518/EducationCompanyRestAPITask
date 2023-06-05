package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.CounterResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoGroup.GroupRequest;
import peaksoft.dto.dtoGroup.GroupResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.exception.MyException;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
    private final CourseRepository courseRepository;
    @Override
    public List<GroupResponse> getAllGroups() {
        return repository.getAllGroups();
    }

    @Override
    public GroupResponse saveGroup(GroupRequest groupRequest, Long courseId) {
        try{

            List<Course> courses = new ArrayList<>();
            Group group = new Group();
            group.setGroupName(groupRequest.name());
            group.setImageLink(groupRequest.imageLink());
            group.setDescription(groupRequest.description());
            group.setCourses(courses);
            group.getCourses().add(courseRepository.findById(courseId).orElseThrow(()->new MyException("Course with Id not found")));
            repository.save(group);
            return GroupResponse.builder()
                    .id(group.getId())
                    .name(group.getGroupName())
                    .imageLink(group.getImageLink())
                    .description(group.getDescription())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public GroupResponse updateGroupById(Long id, GroupRequest groupRequest) {
        try{
            Group group = repository.findById(id).orElseThrow(() -> new MyException("Group with id is not found"));
            group.setGroupName(groupRequest.name());
            group.setImageLink(groupRequest.imageLink());
            group.setDescription( groupRequest.description());
            repository.save(group);
            return GroupResponse
                    .builder()
                    .id(group.getId())
                    .name(group.getGroupName())
                    .imageLink(group.getImageLink())
                    .description(group.getDescription())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        try{
            Group group = repository.findById(id).orElseThrow(() -> new MyException("Group is not found..."));
            return GroupResponse.builder()
                    .id(group.getId())
                    .name(group.getGroupName())
                    .imageLink(group.getImageLink())
                    .description(group.getDescription())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse deleteGroupById(Long id) {
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
                return SimpleResponse.builder()
                        .status("200")
                        .message("Successfully deleted...")
                        .build();
            }else throw new MyException("Group is not found");
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public CounterResponse counter(Long id) {
        try{
            Group group = repository.findById(id).orElseThrow(() -> new MyException("Group is not found..."));
            List<Instructor> instructors = repository.getAllGroupsInstructor(id);
            return CounterResponse.builder()
                    .quantityOfStudents(group.getStudents().size())
                    .quantityOfInstructors(instructors.size())
                    .build();
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
