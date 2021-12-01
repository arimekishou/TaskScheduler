package by.vironit.taskscheduler.controller;

import by.vironit.taskscheduler.converter.AppUserConverter;
import by.vironit.taskscheduler.dto.AppUserDto;
import by.vironit.taskscheduler.repository.AppUserRepository;
import by.vironit.taskscheduler.service.impl.AppUserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log
@RestController
@RequestMapping(value = "/users/")
@AllArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final AppUserServiceImpl userService;
    private final AppUserConverter appUserConverter;
    private final AppUserRepository appUserRepository;

    @GetMapping("/find/all")
    public CollectionModel<AppUserDto> findAllUser(@RequestParam Integer page,
                                                   @RequestParam Integer size,
                                                   @RequestParam String sort) {
        LOGGER.info("Handing find all user request");
        return userService.findAll(page, size, sort);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<EntityModel<AppUserDto>> getUserById(@PathVariable(name = "id") Long id) {

        LOGGER.info("Handling find account request" + userService.getById(id).toString());
        AppUserDto userDto = appUserConverter.fromUserToUserDto(userService.getById(id));
        System.err.println(userDto.toString());
        LOGGER.info("User getting by ID");

        return new ResponseEntity<>(EntityModel.of(userDto,
                linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel()), HttpStatus.OK);
    }

    @GetMapping("/findByEmail")
    public UserDetails findByEmail(@RequestParam String email) {
        LOGGER.info("Handling find by email request: " + email);
        return  userService.loadUserByUsername(email);
    }

    @PutMapping("/update") //не работает
    public ResponseEntity<Void> updateUser(@Valid @AuthenticationPrincipal AppUserDto userDto) {
        LOGGER.info("Handling update user request" + userDto);
        userService.update(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/{id}") //не работает
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {

        if (appUserRepository.existsById(id)) {
            userService.deleteById(id);
            LOGGER.info("Task group deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
