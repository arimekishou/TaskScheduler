package by.vironit.taskscheduler.controller;

import by.vironit.taskscheduler.dto.TaskDto;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log
@RestController
@RequestMapping(value = "/task/")
@AllArgsConstructor
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    private final TaskServiceImpl taskServiceImpl;
    private final TaskRepository taskRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<?> createTask(@Valid @AuthenticationPrincipal
                                        @RequestParam TaskGroups taskGroups,
                                        @RequestParam String title,
                                        @RequestParam String description,
                                        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                LocalDateTime startDate,
                                        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                LocalDateTime endDate, TaskStatus taskStatus) {

        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(title);
        taskDto.setTaskDescription(description);
        taskDto.setTaskGroup(taskGroups);
        taskDto.setStartDate(startDate);
        taskDto.setEndDate(endDate);
        taskDto.setTaskStatus(taskStatus);
        taskServiceImpl.saveTask(taskDto);
        LOGGER.info("Task created");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public CollectionModel<TaskDto> findAll(@RequestParam Integer page,
                                            @RequestParam Integer size,
                                            @RequestParam String sort) {
        LOGGER.info("Handing find all tasks request");
        return taskServiceImpl.findAll(page, size, sort);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<EntityModel<TaskDto>> getTaskById(@PathVariable(name = "id") Long id) {

        TaskDto taskDto = taskServiceImpl.getById(id);
        return new ResponseEntity<>(EntityModel.of(taskDto,
                linkTo(methodOn(TaskController.class).getTaskById(id)).withSelfRel()), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable(name = "id") Long id, TaskDto taskDto, Task task) {

        if (taskRepository.existsById(id)) {

            LOGGER.info("Handling update task request" + task);

            if (taskDto.getTitle() != null) {
                task.setTitle(taskDto.getTitle());
            }
            if (taskDto.getTaskDescription() != null) {
                task.setTaskDescription(taskDto.getTaskDescription());
            }
            if (taskDto.getStartDate() != null) {
                task.setStartDate(taskDto.getStartDate());
            }
            if (taskDto.getEndDate() != null) {
                task.setEndDate(taskDto.getEndDate());
            }
            if (taskDto.getTaskStatus() != null) {
                task.setTaskStatus(taskDto.getTaskStatus());
            }

            taskServiceImpl.updateTask(taskDto);

            return ResponseEntity.ok().build();
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
