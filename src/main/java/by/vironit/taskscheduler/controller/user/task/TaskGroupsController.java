package by.vironit.taskscheduler.controller.user.task;

import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.service.TaskGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/taskGroups/")
public class TaskGroupsController {

    private final TaskGroupsService taskGroupsService;


    @Autowired
    public TaskGroupsController(TaskGroupsService taskGroupsService) {
        this.taskGroupsService = taskGroupsService;
    }


    @PostMapping(value = "add")
    public ResponseEntity<TaskGroups> saveCustomer(@RequestBody @Valid TaskGroups taskGroups) {
        HttpHeaders headers = new HttpHeaders();

        if (taskGroups == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.taskGroupsService.saveTaskGroups(taskGroups);
        return new ResponseEntity<>(taskGroups, headers, HttpStatus.CREATED);
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

}
