package by.vironit.taskscheduler.assembler;


import by.vironit.taskscheduler.controller.UserController;
import by.vironit.taskscheduler.dto.AppUserDto;
import by.vironit.taskscheduler.entities.AppUser;
import lombok.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppUserAssembler extends RepresentationModelAssemblerSupport<AppUser, AppUserDto> {

    public AppUserAssembler() {
        super(UserController.class, AppUserDto.class);
    }

    @Override
    @NonNull
    public CollectionModel<AppUserDto> toCollectionModel(@NonNull Iterable<? extends AppUser> entities) {

        CollectionModel<AppUserDto> userModels = super.toCollectionModel(entities);
        userModels.add(linkTo(methodOn(UserController.class).findAllUser(null, null, null)).withSelfRel());

        return super.toCollectionModel(entities);
    }

    @Override
    @NonNull
    public AppUserDto toModel(@NonNull AppUser user) {

        AppUserDto userDto = instantiateModel(user);

        userDto.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}

