package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class TaskGroupsService {

    private final TaskGroupsRepository taskGroupsRepository;

    public List<TaskGroups> findAll() {
        return taskGroupsRepository.findAll();
    }

    public void saveTaskGroup(TaskGroups taskGroups) {
        taskGroupsRepository.save(taskGroups);
    }

    public void deleteById(Long id) {
        taskGroupsRepository.deleteById(id);
    }

    public TaskGroups findById(Long id) {
        return taskGroupsRepository.getOne(id);
    }

}
