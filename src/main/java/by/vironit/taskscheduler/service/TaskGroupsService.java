package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface TaskGroupsService {

    TaskGroupsDto saveTaskGroup(TaskGroupsDto taskGroupsDto);

    TaskGroupsDto getById(Long id);

    TaskGroupsDto findByTitle(String title);

    CollectionModel<TaskGroupsDto> findAll(Integer page, Integer size, String sort);

    List<TaskGroups> getAllByAppUser(AppUser appUser);

    void deleteById(Long id);

    void updateTaskGroup(TaskGroupsDto taskGroupsDto);

}
