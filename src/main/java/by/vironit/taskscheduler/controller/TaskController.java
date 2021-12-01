package by.vironit.taskscheduler.controller;

import by.vironit.taskscheduler.dto.TaskDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.enums.TaskStatus;
import by.vironit.taskscheduler.repository.TaskRepository;
import by.vironit.taskscheduler.service.impl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    private final TaskServiceImpl taskServiceImpl;
    private final TaskRepository taskRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<?> createTask(@Valid @AuthenticationPrincipal AppUser appUser,
                                        @RequestParam String title,
                                        @RequestParam String description,
                                        @RequestParam Long taskGroupsId,
                                        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                LocalDateTime startDate,
                                        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                LocalDateTime endDate, TaskStatus taskStatus) {

        /*Task task = new Task(taskGroups, title, description, startDate, endDate, taskStatus);
        taskService.saveTask(task);
        LOGGER.info("Task created");*/
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<Task>> findAll() {

        final List<Task> task = taskServiceImpl.findAll();

        return task != null && !task.isEmpty()
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "id") Long id) {

        Task task = taskServiceImpl.findById(id);

        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        TaskDto result = TaskDto.fromTask(task);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @Valid @AuthenticationPrincipal
            String title, TaskGroups taskGroups, String taskDescription, LocalDateTime startDate,
                                    LocalDateTime endDate, TaskStatus status) {

        if (taskRepository.existsById(id)) {

            Task task = new Task();
            task.setId(id);
            task.setTitle(title);
            task.setTaskGroup(taskGroups);
            task.setTaskDescription(taskDescription);
            task.setStartDate(startDate);
            task.setEndDate(endDate);
            task.setTaskStatus(status);
            taskServiceImpl.saveTask(task);
            LOGGER.info("Task updated");
            return new ResponseEntity<>(HttpStatus.OK);

        } else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping(value = "/find/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {

        if (taskRepository.existsById(id)) {
            taskServiceImpl.deleteById(id);
            LOGGER.info("Task deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
