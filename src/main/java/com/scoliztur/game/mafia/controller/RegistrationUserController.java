package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.User;
import com.scoliztur.game.mafia.filters.validator.Validator;
import com.scoliztur.game.mafia.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationUserController {

    private final UserService userService;
    private final Validator validator;

    public RegistrationUserController(UserService userService) {
        this.userService = userService;
        this.validator = new Validator();
    }


    @PostMapping("/sign_up")
    public String userRegistration(@RequestBody User user) {

        if(validator.userValidate(user)) {
            if (user != null) {
                user = userService.register(user);
            }
        } else return "Not valid request body";

        assert user != null;
        return user.getUsername();
    }
}
