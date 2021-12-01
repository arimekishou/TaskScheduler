package by.vironit.taskscheduler.controller;

import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import by.vironit.taskscheduler.service.impl.TaskGroupsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/taskGroups/")
@AllArgsConstructor
@Log
public class TaskGroupsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskGroupsController.class);
    private final TaskGroupsServiceImpl taskGroupsServiceImpl;
    private final TaskGroupsRepository taskGroupsRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<?> createGroup(@Valid @AuthenticationPrincipal AppUser appUser, String title) {

        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        taskGroupsDto.setTitle(title);
        taskGroupsDto.setApp_user_id(appUser);
        taskGroupsServiceImpl.saveTaskGroup(taskGroupsDto);
        LOGGER.info("Task group created");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/find/all")
    public CollectionModel<TaskGroupsDto> findAll() {
        LOGGER.info("Handing find all task groups request");
        Integer page = 0;
        Integer size = 5;
        Specification<TaskGroups> spec = null;
        String sort = "id";
        LOGGER.info("FIND task groups");
        return taskGroupsServiceImpl.findAll(page, size, sort);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<EntityModel<TaskGroupsDto>> getGroupById(@PathVariable(name = "id") Long id) {

        TaskGroupsDto taskGroupsDto = taskGroupsServiceImpl.getById(id);

        return new ResponseEntity<>(EntityModel.of(taskGroupsDto,
                linkTo(methodOn(TaskGroupsController.class).getGroupById(id)).withSelfRel()), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @Valid @AuthenticationPrincipal AppUser appUser, String title) {

        if (taskGroupsRepository.existsById(id)) {

            TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
            taskGroupsDto.setApp_user_id(appUser);
            taskGroupsDto.setTitle(title);
            taskGroupsServiceImpl.updateTaskGroup(taskGroupsDto);
            LOGGER.info("Task group updated");
            return ResponseEntity.ok().build();

        } else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {

        if (taskGroupsRepository.existsById(id)) {
            taskGroupsServiceImpl.deleteById(id);
            LOGGER.info("Task group deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
