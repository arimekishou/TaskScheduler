package by.vironit.taskscheduler.dto;

import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import lombok.Data;

@Data
public class TaskGroupsDto {

    private Long id;
    private AppUser app_user_id;
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
