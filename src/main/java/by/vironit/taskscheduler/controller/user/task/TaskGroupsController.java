package by.vironit.taskscheduler.controller.user.task;

import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import by.vironit.taskscheduler.service.TaskGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/taskGroups/")
public class TaskGroupsController {

    private final TaskGroupsService taskGroupsService;
    private final TaskGroupsRepository taskGroupsRepository;

    @Autowired
    public TaskGroupsController(TaskGroupsService taskGroupsService, TaskGroupsRepository taskGroupsRepository) {
        this.taskGroupsService = taskGroupsService;
        this.taskGroupsRepository = taskGroupsRepository;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> createGroup(@RequestBody TaskGroups taskGroups) {
        taskGroupsService.saveTaskGroup(taskGroups);
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
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, String title,
                                    @RequestBody TaskGroups taskGroups) {
        if (taskGroupsRepository.existsById(id)) {
            taskGroups.setId(id);
            taskGroups.setTitle(title);
            final TaskGroups update = taskGroupsRepository.save(taskGroups);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/find/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        if (taskGroupsRepository.existsById(id)) {
            taskGroupsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
