package by.vironit.taskscheduler.controller;

import by.vironit.taskscheduler.assembler.AppUserAssembler;
import by.vironit.taskscheduler.converter.AppUserConverter;
import by.vironit.taskscheduler.dto.AppUserDto;
import by.vironit.taskscheduler.dto.TaskGroupsDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.entities.TaskGroups;
import by.vironit.taskscheduler.repository.AppUserRepository;
import by.vironit.taskscheduler.service.impl.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;

@ContextConfiguration(classes = {AppUserServiceImpl.class,
        AppUserRepository.class,
        AppUserConverter.class,
        AppUserAssembler.class})
class UserControllerTest {

    @Autowired
    @InjectMocks
    private AppUserServiceImpl appUserService;

    @Autowired
    @Mock
    private AppUserConverter appUserConverter;

    @Mock
    private AppUserRepository appUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAllUser() {
        AppUser appUser = new AppUser();
        AppUserDto appUserDto = new AppUserDto();
        Mockito.when(appUserRepository.findAll()).thenReturn(Collections.singletonList(appUser));
        Mockito.when(appUserConverter.fromUserToUserDto(appUser)).thenReturn(appUserDto);
    }

    @Test
    void getUserById() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void delete() {
    }
}