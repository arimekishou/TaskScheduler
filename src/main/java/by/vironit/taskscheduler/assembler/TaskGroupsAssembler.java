package by.vironit.taskscheduler.assembler;

import by.vironit.taskscheduler.controller.TaskGroupsController;
import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.TaskGroups;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskGroupsAssembler extends RepresentationModelAssemblerSupport<TaskGroups, TaskGroupsDto> {

    public TaskGroupsAssembler() {
        super(TaskGroupsController.class, TaskGroupsDto.class);
    }

    @Override
    public CollectionModel<TaskGroupsDto> toCollectionModel(Iterable<? extends TaskGroups> entities) {

        CollectionModel<TaskGroupsDto> taskGroupsDtoCollectionModel = super.toCollectionModel(entities);
        taskGroupsDtoCollectionModel.add(linkTo(methodOn(TaskGroupsController.class).findAll()).withSelfRel());

        return super.toCollectionModel(entities);
    }

    @Override
    public TaskGroupsDto toModel(TaskGroups taskGroups) {

        TaskGroupsDto taskGroupsDto = instantiateModel(taskGroups);

        taskGroupsDto.add(linkTo(methodOn(TaskGroupsController.class).getGroupById(taskGroups.getId())).withSelfRel());
        taskGroupsDto.setId(taskGroups.getId());
        taskGroupsDto.setApp_user_id(taskGroups.getAppUser());
        taskGroupsDto.setTitle(taskGroups.getTitle());

        return taskGroupsDto;
    }

}
