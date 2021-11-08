package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskGroupsService {

    private final TaskGroupsRepository taskGroupsRepository;

}
