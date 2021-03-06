package by.vironit.taskscheduler.dto;

import by.vironit.taskscheduler.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskGroupsDto extends RepresentationModel<TaskGroupsDto> {

    @Null
    private Long id;
    @Null
    private AppUser app_user_id;
    @NotNull
    private String title;

}
