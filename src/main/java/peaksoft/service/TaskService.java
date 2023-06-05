package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoTask.TaskRequest;
import peaksoft.dto.dtoTask.TaskResponse;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTasks();
    TaskResponse saveTask(TaskRequest taskRequest, Long lessonId);
    TaskResponse updateTaskById(Long id, TaskRequest taskRequest);
    TaskResponse getTaskById(Long id);
    SimpleResponse deleteTaskById(Long id);
}
