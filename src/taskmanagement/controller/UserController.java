package taskmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import taskmanagement.entity.AppUser;
import taskmanagement.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public void register(@Valid @RequestBody AppUser user) {
        service.saveUser(user);
    }
}
