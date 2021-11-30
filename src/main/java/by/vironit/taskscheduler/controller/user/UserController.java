package by.vironit.taskscheduler.controller.user;

import by.vironit.taskscheduler.dto.UserDto;
import by.vironit.taskscheduler.entities.AppUser;
import by.vironit.taskscheduler.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final AppUserService userService;

    @Autowired
    public UserController(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EntityModel<UserDto>> getUserById(@PathVariable(name = "id") Long id) {

        AppUser user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);
        LOGGER.info("User getting by ID");
        return new ResponseEntity<>(EntityModel.of(result,
                linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel()), HttpStatus.OK);
    }

}
