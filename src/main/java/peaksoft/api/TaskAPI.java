package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoTask.TaskRequest;
import peaksoft.dto.dtoTask.TaskResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskAPI {
    private final TaskService taskService;

    @PermitAll
    @GetMapping
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping
    public TaskResponse saveTask(@RequestBody TaskRequest taskRequest, @RequestParam Long lessonId){
        return taskService.saveTask(taskRequest,lessonId);
    }
    @PutMapping("{id}")
    public TaskResponse updateTaskById(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        return taskService.updateTaskById(id,taskRequest);
    }

    @PermitAll
    @GetMapping("{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/delete-task/{id}")
    public SimpleResponse deleteTaskById(@PathVariable Long id){
        return taskService.deleteTaskById(id);
    }
}
