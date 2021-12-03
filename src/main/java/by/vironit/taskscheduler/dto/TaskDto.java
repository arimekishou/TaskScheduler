package by.vironit.taskscheduler.dto;

import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.entities.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto extends RepresentationModel<TaskDto> {

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

}
