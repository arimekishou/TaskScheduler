package by.vironit.taskscheduler.service.impl;

import by.vironit.taskscheduler.assembler.TaskAssembler;
import by.vironit.taskscheduler.converter.TaskConverter;
import by.vironit.taskscheduler.dto.TaskDto;
import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.enums.TaskStatus;
import by.vironit.taskscheduler.repository.TaskRepository;
import by.vironit.taskscheduler.service.TaskService;
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
public class TaskServiceImpl implements TaskService {

    private final TaskConverter taskConverter;
    private final TaskRepository taskRepository;
    private TaskAssembler assembler;

    @Override
    public TaskDto saveTask(TaskDto taskDto) {
        Task task = taskRepository.save(taskConverter.fromTaskDtoToTask(taskDto));
        return taskConverter.fromTaskToTaskDto(task);
    }

    @Override
    public TaskDto getById(Long id) {
        Task task = taskRepository.getById(id);
        return taskConverter.fromTaskToTaskDto(task);
    }

    @Override
    public TaskDto findByTitle(String title) {
        Task task = taskRepository.findByTitle(title);
        if (task != null) {
            return taskConverter.fromTaskToTaskDto(task);
        }
        return null;
    }

    @Override
    public CollectionModel<TaskDto> findAll(Integer page, Integer size, String sort) {
        int pages = page != null ? page : 0;
        int sizes = size != null ? size : 5;
        String sorts = sort != null && !sort.equals("") ? sort : "name";
        Pageable pageable = PageRequest.of(pages, sizes, Sort.by(sorts));
        Page<Task> tasks = taskRepository.findAll(pageable);
        return !tasks.isEmpty() ? assembler.toCollectionModel(tasks) : null;
    }

    @Override
    public List<Task> getAllByTaskStatus(TaskStatus taskStatus) {
        return taskRepository.getAllByTaskStatus(taskStatus);
    }

    @Override
    public List<Task> getAllByTaskGroup(TaskGroups taskGroups) {
        return taskRepository.getAllByTaskGroup(taskGroups);
    }

    @Override
    public void deleteByTaskGroupId(Long id) {
        taskRepository.deleteTaskByTaskGroupId(id);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        taskRepository.save(taskConverter.fromTaskDtoToTask(taskDto));
    }

}
