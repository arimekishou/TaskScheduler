package by.vironit.taskscheduler.service.impl;

import by.vironit.taskscheduler.assembler.TaskGroupsAssembler;
import by.vironit.taskscheduler.converter.TaskGroupsConverter;
import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import by.vironit.taskscheduler.service.TaskGroupsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskGroupsServiceImpl implements TaskGroupsService {


    private final TaskGroupsConverter taskGroupsConverter;
    private final TaskGroupsRepository taskGroupsRepository;
    private TaskGroupsAssembler assembler;

    @Override
    public TaskGroupsDto saveTaskGroup(TaskGroupsDto taskGroupsDto) {

        TaskGroups taskGroups = taskGroupsRepository.save(taskGroupsConverter.fromTaskGroupsDtoToTaskGroups(taskGroupsDto));

        return taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups);
    }

    @Override
    public TaskGroups getById(Long id) {

        if (taskGroupsRepository.getById(id) != null) {
            return taskGroupsRepository.getById(id);
        }
        return null;
    }

    @Override
    public CollectionModel<TaskGroupsDto> findAll(Integer page, Integer size, String sort) {

        int pages = page != null ? page : 0;
        int sizes = size != null ? size : 5;
        String sorts = sort != null && !sort.equals("") ? sort : "name";
        Pageable pageable = PageRequest.of(pages, sizes, Sort.by(sorts));
        Page<TaskGroups> taskGroups = taskGroupsRepository.findAll(pageable);

        return !taskGroups.isEmpty() ? assembler.toCollectionModel(taskGroups) : null;
    }

    @Override
    public List<TaskGroups> getAllByAppUser(AppUser appUser) {
        return taskGroupsRepository.getAllByAppUser(appUser);
    }

    @Override
    public void updateTaskGroup(TaskGroupsDto taskGroupsDto) {
        taskGroupsRepository.save(taskGroupsConverter.fromTaskGroupsDtoToTaskGroups(taskGroupsDto));
    }

    @Override
    public TaskGroupsDto findByTitle(String title) {

        TaskGroups taskGroups = taskGroupsRepository.findByTitle(title);

        if (taskGroups != null) {
            return taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups);
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        taskGroupsRepository.deleteById(id);
    }

}
