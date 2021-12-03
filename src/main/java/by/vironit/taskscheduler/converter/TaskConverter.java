package by.vironit.taskscheduler.converter;

import by.vironit.taskscheduler.dto.TaskDto;
import by.vironit.taskscheduler.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public static TaskDto getTaskDto(Task task, TaskDto taskDto) {

        taskDto.setId(task.getId());
        taskDto.setTaskGroup(task.getTaskGroup());
        taskDto.setTitle(task.getTitle());
        taskDto.setTaskDescription(task.getTaskDescription());
        taskDto.setStartDate(task.getStartDate());
        taskDto.setEndDate(task.getEndDate());
        taskDto.setTaskStatus(task.getTaskStatus());


        return taskDto;
    }

    public Task fromTaskDtoToTask(TaskDto taskDto) {

        Task task = new Task();
        task.setTaskGroup(taskDto.getTaskGroup());
        task.setTitle(taskDto.getTitle());
        task.setTaskDescription(taskDto.getTaskDescription());
        task.setStartDate(taskDto.getStartDate());
        task.setEndDate(taskDto.getEndDate());
        task.setTaskStatus(taskDto.getTaskStatus());

        return task;
    }

    public TaskDto fromTaskToTaskDto(Task task) {

        TaskDto taskDto = new TaskDto();
        return getTaskDto(task, taskDto);
    }
}
