package by.vironit.taskscheduler.dto;

import by.vironit.taskscheduler.entities.Task;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {

    @Null
    private Long id;
    @Null
    private TaskGroups taskGroup;
    @NotEmpty(message = "Write Some Title")
    private String title;
    @NotEmpty(message = "Write Some Description")
    private String taskDescription;
    @DateTimeFormat
    @FutureOrPresent
    private LocalDateTime startDate;
    @DateTimeFormat
    @FutureOrPresent
    private LocalDateTime endDate;
    @Enumerated
    private TaskStatus taskStatus;

    public static TaskDto fromTask(Task task) {

        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTaskGroup(task.getTaskGroup());
        taskDto.setTitle(task.getTitle());
        taskDto.setTaskDescription(task.getTaskDescription());
        taskDto.setStartDate(task.getStartDate());
        taskDto.setEndDate(task.getEndDate());
        taskDto.setTaskStatus(taskDto.getTaskStatus());

        return taskDto;
    }

    public Task toTask() {

        Task task = new Task();
        task.setId(id);
        task.setTaskGroup(taskGroup);
        task.setTitle(title);
        task.setTaskDescription(taskDescription);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setTaskStatus(taskStatus);

        return task;
    }

}
