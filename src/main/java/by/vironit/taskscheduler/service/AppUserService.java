package by.vironit.taskscheduler.service;

import by.vironit.taskscheduler.dto.AppUserDto;
import by.vironit.taskscheduler.entities.AppUser;
import org.springframework.hateoas.CollectionModel;

public interface AppUserService {

    CollectionModel<AppUserDto> findAll(Integer page, Integer size, String sort);

    AppUser getById(Long id);

    void deleteById(Long id);

}
