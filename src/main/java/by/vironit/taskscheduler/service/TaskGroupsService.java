package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskGroupsService {

    private final TaskGroupsRepository taskGroupsRepository;

    public List<TaskGroups> findAll(@Valid TaskGroups taskGroups) {
        return taskGroupsRepository.findAll();
    }

    public TaskGroups saveTaskGroups(TaskGroups taskGroups) {
        return taskGroupsRepository.save(taskGroups);
    }

    public void deleteById(Long id) {
        taskGroupsRepository.deleteById(id);
    }

    public TaskGroups findById(Long id) {
        return taskGroupsRepository.getOne(id);
    }

}
