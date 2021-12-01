package by.vironit.taskscheduler.converter;

import by.vironit.taskscheduler.dto.AppUserDto;
import by.vironit.taskscheduler.entities.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserConverter {

    public AppUser fromUserDtoToUser(AppUserDto userDto) {

        AppUser user = new AppUser();
        user.setFirstName(user.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());

        return user;
    }

    public AppUserDto fromUserToUserDto(AppUser user) {

        AppUserDto userDto = new AppUserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}