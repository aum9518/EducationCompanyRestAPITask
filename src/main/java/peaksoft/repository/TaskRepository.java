package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.dtoTask.TaskResponse;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select new peaksoft.dto.dtoTask.TaskResponse(t.id,t.taskName,t.taskText,t.deadLine) from Task t")
    List<TaskResponse> getAllTasks();
}