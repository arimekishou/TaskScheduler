package by.vironit.taskscheduler.dto;

import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskGroupsDto {

    @Null
    private Long id;
    @Null
    private AppUser app_user_id;
    @NotNull
    private String title;

    public static TaskGroupsDto fromTaskGroup(TaskGroups taskGroups) {

        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        taskGroupsDto.setId(taskGroups.getId());
        taskGroupsDto.setApp_user_id(taskGroups.getAppUser());
        taskGroupsDto.setTitle(taskGroups.getTitle());

        return taskGroupsDto;
    }

    public TaskGroups toTaskGroups() {

        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setId(id);
        taskGroups.setAppUser(app_user_id);
        taskGroups.setTitle(title);

        return taskGroups;
    }

}
