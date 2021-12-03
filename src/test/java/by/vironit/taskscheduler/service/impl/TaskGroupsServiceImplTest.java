package by.vironit.taskscheduler.service.impl;

import by.vironit.taskscheduler.assembler.TaskGroupsAssembler;
import by.vironit.taskscheduler.converter.TaskGroupsConverter;
import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.TaskGroupsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ContextConfiguration(classes = {TaskGroupsServiceImpl.class,
        TaskGroupsRepository.class,
        TaskGroupsConverter.class,
        TaskGroupsAssembler.class})
class TaskGroupsServiceImplTest {


    @Autowired
    @InjectMocks
    private TaskGroupsServiceImpl taskGroupsService;

    @Autowired
    @Mock
    private TaskGroupsConverter taskGroupsConverter;

    @Mock
    private TaskGroupsRepository taskGroupsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveTaskGroup() {
        long id = 1;
        String title = "hello";
        AppUser appUser = new AppUser();
        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setId(id);
        taskGroups.setTitle(title);
        taskGroups.setAppUser(appUser);
        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        taskGroupsDto.setId(id);
        taskGroupsDto.setTitle(title);
        taskGroupsDto.setApp_user_id(appUser);
        Mockito.when(taskGroupsRepository.save(taskGroups)).thenReturn(taskGroups);
        Mockito.when(taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups)).thenReturn(taskGroupsDto);
    }

    @Test
    void getById() {
        long id = 1;
        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setId(id);
        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        taskGroupsDto.setId(id);
        Mockito.when(taskGroupsRepository.getById(id)).thenReturn(taskGroups);
        Mockito.when(taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups)).thenReturn(taskGroupsDto);
        TaskGroups expected = taskGroupsService.getById(id);
        TaskGroupsDto actual = new TaskGroupsDto();
        actual.setId(id);
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        TaskGroups taskGroups = new TaskGroups();
        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        Mockito.when(taskGroupsRepository.findAll()).thenReturn(Collections.singletonList(taskGroups));
        Mockito.when(taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups)).thenReturn(taskGroupsDto);
    }

    @Test
    void getAllByAppUser() {
        AppUser appUser = new AppUser();
        TaskGroups taskGroups = new TaskGroups();
        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        Mockito.when(taskGroupsRepository.getAllByAppUser(appUser)).thenReturn(Collections.singletonList(taskGroups));
        Mockito.when(taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups)).thenReturn(taskGroupsDto);
    }

    @Test
    void updateTaskGroup() {
        TaskGroups taskGroups = new TaskGroups();
        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        Mockito.when(taskGroupsRepository.findAll()).thenReturn(Collections.singletonList(taskGroups));
        taskGroups.setTitle("hi");
        Mockito.when(taskGroupsRepository.save(taskGroups)).thenReturn(taskGroups);
        Mockito.when(taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups)).thenReturn(taskGroupsDto);
    }

    @Test
    void findByTitlePositive() {
        String title = "hello";
        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setTitle(title);
        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        taskGroupsDto.setTitle(title);
        Mockito.when(taskGroupsRepository.findByTitle(title)).thenReturn(taskGroups);
        Mockito.when(taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups)).thenReturn(taskGroupsDto);
        TaskGroupsDto expected = taskGroupsService.findByTitle(title);
        TaskGroupsDto actual = new TaskGroupsDto();
        actual.setTitle(title);
        assertEquals(expected, actual);
    }

    @Test
    public void findByTitleNegative() {
        String title = "hello";
        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setTitle(title);
        TaskGroupsDto taskGroupsDto = new TaskGroupsDto();
        taskGroupsDto.setTitle(title);
        Mockito.when(taskGroupsRepository.findByTitle(title)).thenReturn(taskGroups);
        Mockito.when(taskGroupsConverter.fromTaskGroupsToTaskGroupsDto(taskGroups)).thenReturn(taskGroupsDto);
        TaskGroupsDto expected = taskGroupsService.findByTitle(title);
        TaskGroupsDto actual = new TaskGroupsDto();
        actual.setTitle("hi");
        assertNotEquals(expected, actual);

    }


    @Test
    void deleteById() {
        long id = 1;
        TaskGroups taskGroups = new TaskGroups();
        taskGroups.setId(id);
        taskGroupsService.deleteById(id);
    }
}