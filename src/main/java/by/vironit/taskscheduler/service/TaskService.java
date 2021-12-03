package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.dto.TaskDto;
import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.enums.TaskStatus;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface TaskService {

    TaskDto saveTask(TaskDto taskDto);

    TaskDto getById(Long id);

    TaskDto findByTitle(String title);

    CollectionModel<TaskDto> findAll(Integer page, Integer size, String sort);

    List<Task> getAllByTaskStatus(TaskStatus taskStatus);

    List<Task> getAllByTaskGroup(TaskGroups taskGroups);

    void deleteById(Long id);

    void updateTask(TaskDto taskDto);

}
