package by.vironit.taskscheduler.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(RegistrationRequest request, HttpServletResponse response) throws IOException {
        registrationService.register(request);
        response.sendRedirect("/registrationConfirm");
        return null;

    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        registrationService.confirmToken(token);
        response.sendRedirect("/login");
        return null;
    }

}
