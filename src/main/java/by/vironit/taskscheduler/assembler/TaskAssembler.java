package by.vironit.taskscheduler.assembler;

import by.vironit.taskscheduler.controller.TaskController;
import by.vironit.taskscheduler.controller.TaskGroupsController;
import by.vironit.taskscheduler.dto.TaskDto;
import by.vironit.taskscheduler.entities.Task;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static by.vironit.taskscheduler.converter.TaskConverter.getTaskDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskAssembler extends RepresentationModelAssemblerSupport<Task, TaskDto> {

    public TaskAssembler() {
        super(TaskController.class, TaskDto.class);
    }

    @Override
    public CollectionModel<TaskDto> toCollectionModel(Iterable<? extends Task> entities) {

        CollectionModel<TaskDto> taskDtoCollectionModel = super.toCollectionModel(entities);
        taskDtoCollectionModel.add(linkTo(methodOn(TaskController.class).findAll(null, null, null)).withSelfRel());

        return super.toCollectionModel(entities);
    }

    @Override
    public TaskDto toModel(Task task) {

        TaskDto taskDto = instantiateModel(task);

        taskDto.add(linkTo(methodOn(TaskGroupsController.class).getGroupById(task.getId())).withSelfRel());
        return getTaskDto(task, taskDto);
    }

}
