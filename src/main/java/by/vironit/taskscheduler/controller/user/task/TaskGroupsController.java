package by.vironit.taskscheduler.controller.user.task;

import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import by.vironit.taskscheduler.service.TaskGroupsService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/taskGroups/")
@AllArgsConstructor
@Log
public class TaskGroupsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskGroupsController.class);
    private final TaskGroupsService taskGroupsService;
    private final TaskGroupsRepository taskGroupsRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<?> createGroup(@Valid @AuthenticationPrincipal AppUser appUser, String title) {

        TaskGroups taskGroups = new TaskGroups(title, appUser);
        taskGroupsService.saveTaskGroup(taskGroups);
        LOGGER.info("Task group created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/all")
    public ResponseEntity<List<TaskGroups>> findAll() {

        final List<TaskGroups> taskGroups = taskGroupsService.findAll();

        return taskGroups != null && !taskGroups.isEmpty()
                ? new ResponseEntity<>(taskGroups, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<TaskGroupsDto> getGroupById(@PathVariable(name = "id") Long id) {

        TaskGroups taskGroups = taskGroupsService.findById(id);

        if (taskGroups == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        TaskGroupsDto result = TaskGroupsDto.fromTaskGroup(taskGroups);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @Valid @AuthenticationPrincipal AppUser appUser, String title) {

        if (taskGroupsRepository.existsById(id)) {

            TaskGroups taskGroups = new TaskGroups();
            taskGroups.setId(id);
            taskGroups.setTitle(title);
            taskGroups.setAppUser(appUser);
            taskGroupsService.saveTaskGroup(taskGroups);
            LOGGER.info("Task group updated");
            return new ResponseEntity<>(HttpStatus.OK);

        } else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping(value = "/find/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {

        if (taskGroupsRepository.existsById(id)) {
            taskGroupsService.deleteById(id);
            LOGGER.info("Task group deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
