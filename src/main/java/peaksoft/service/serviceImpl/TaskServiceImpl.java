package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoTask.TaskRequest;
import peaksoft.dto.dtoTask.TaskResponse;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.exception.MyException;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final LessonRepository lessonRepository;

    @Override
    public List<TaskResponse> getAllTasks() {
        return repository.getAllTasks();
    }

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest, Long lessonId) {
        try{
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new MyException("The task is not found"));

            Task task = new Task();
            task.setLesson(lesson);
            task.setTaskName(taskRequest.taskName());
            task.setTaskText(taskRequest.taskText());
            task.setDeadLine(taskRequest.deadLine());
            repository.save(task);
            return TaskResponse.builder()
                    .id(task.getId())
                    .taskName(task.getTaskName())
                    .taskText(task.getTaskText())
                    .deadLine(task.getDeadLine())
                    .build();

        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public TaskResponse updateTaskById(Long id, TaskRequest taskRequest) {
        try{
            Task task = repository.findById(id).orElseThrow(() -> new MyException("The task is not found"));
            task.setTaskName(taskRequest.taskName());
            task.setTaskText(taskRequest.taskText());
            task.setDeadLine(taskRequest.deadLine());
            return TaskResponse.builder()
                    .id(task.getId())
                    .taskName(task.getTaskName())
                    .taskText(task.getTaskText())
                    .deadLine(task.getDeadLine())
                    .build();

        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        try{
            Task task = repository.findById(id).orElseThrow(() -> new MyException("The Task is not found"));
            return TaskResponse.builder()
                    .id(task.getId())
                    .taskName(task.getTaskName())
                    .taskText(task.getTaskText())
                    .deadLine(task.getDeadLine())
                    .build();
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public SimpleResponse deleteTaskById(Long id) {
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
                return SimpleResponse.builder()
                        .status("200")
                        .message("The task is successfully deleted")
                        .build();
            }else  throw new MyException("The task is not found");
        }catch(MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
