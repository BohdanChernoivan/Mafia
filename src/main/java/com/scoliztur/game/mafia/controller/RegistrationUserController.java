package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.User;
import com.scoliztur.game.mafia.filters.validator.Validator;
import com.scoliztur.game.mafia.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
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
