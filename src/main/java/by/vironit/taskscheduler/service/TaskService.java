package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> allTask() {
        return taskRepository.findAll();
    }

    public void save(Task task) {
        taskRepository.saveAndFlush(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

    public Optional<Task> getById(Task task) {
        return taskRepository.findById(task.getId());
    }

}
