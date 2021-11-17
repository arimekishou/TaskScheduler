package by.vironit.taskscheduler.controller.user.task;

import by.vironit.taskscheduler.dto.TaskDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.TaskStatus;
import by.vironit.taskscheduler.repository.TaskRepository;
import by.vironit.taskscheduler.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Log
@RestController
@RequestMapping(value = "/task/")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<?> createTask(@Valid @AuthenticationPrincipal TaskGroups taskGroups, String title,
                                        String description, TaskStatus taskStatus, LocalDateTime startDate,
                                        LocalDateTime endDate) {

        Task task = new Task(taskGroups, title, description, startDate, endDate, taskStatus);
        taskService.saveTask(task);
        log.info("added");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Task>> findAll() {

        final List<Task> task = taskService.findAll();

        return task != null && !task.isEmpty()
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "id") Long id) {

        Task task = taskService.findById(id);

        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        TaskDto result = TaskDto.fromTask(task);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @Valid @AuthenticationPrincipal AppUser appUser,
                                    String title, TaskGroups taskGroups, String taskDescription, LocalDateTime startDate,
                                    LocalDateTime endDate, TaskStatus status) {

        if (taskRepository.existsById(id)) {

            Task task = new Task();
            task.setId(id);
            task.setTitle(title);
            task.setTaskGroup(taskGroups);
            task.setTitle(title);
            task.setTaskDescription(taskDescription);
            task.setStartDate(startDate);
            task.setEndDate(endDate);
            task.setTaskStatus(status);
            taskService.saveTask(task);

            return new ResponseEntity<>(HttpStatus.OK);

        } else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping(value = "/find/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {

        if (taskRepository.existsById(id)) {
            taskService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
