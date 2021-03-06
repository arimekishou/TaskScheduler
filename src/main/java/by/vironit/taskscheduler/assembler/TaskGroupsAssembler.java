package by.vironit.taskscheduler.assembler;

import by.vironit.taskscheduler.controller.TaskGroupsController;
import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.TaskGroups;
import lombok.NonNull;
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
    @NonNull
    public CollectionModel<TaskGroupsDto> toCollectionModel(@NonNull Iterable<? extends TaskGroups> entities) {

        CollectionModel<TaskGroupsDto> taskGroupsDtoCollectionModel = super.toCollectionModel(entities);
        taskGroupsDtoCollectionModel.add(linkTo(methodOn(TaskGroupsController.class)
                .findAll(null, null, null)).withSelfRel());

        return super.toCollectionModel(entities);
    }

    @Override
    @NonNull
    public TaskGroupsDto toModel(@NonNull TaskGroups taskGroups) {

        TaskGroupsDto taskGroupsDto = instantiateModel(taskGroups);

        taskGroupsDto.add(linkTo(methodOn(TaskGroupsController.class).getGroupById(taskGroups.getId())).withSelfRel());
        taskGroupsDto.setId(taskGroups.getId());
        taskGroupsDto.setApp_user_id(taskGroups.getAppUser());
        taskGroupsDto.setTitle(taskGroups.getTitle());

        return taskGroupsDto;
    }

}
