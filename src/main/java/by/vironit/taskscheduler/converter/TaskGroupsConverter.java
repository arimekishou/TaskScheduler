package by.vironit.taskscheduler.converter;

import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.TaskGroups;
import org.springframework.stereotype.Component;

@Component
public class TaskGroupsConverter {

    public TaskGroups fromTaskGroupsDtoToTaskGroups(TaskGroupsDto taskGroupsDto) {

        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setTitle(taskGroupsDto.getTitle());
        taskGroups.setAppUser(taskGroupsDto.getApp_user_id());

        return taskGroups;
    }

    public TaskGroupsDto fromTaskGroupsToTaskGroupsDto(TaskGroups taskGroups) {

        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        taskGroupsDto.setId(taskGroups.getId());
        taskGroupsDto.setApp_user_id(taskGroups.getAppUser());
        taskGroupsDto.setTitle(taskGroups.getTitle());

        return taskGroupsDto;
    }

}
