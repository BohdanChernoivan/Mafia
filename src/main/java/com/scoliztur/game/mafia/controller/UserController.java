package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.services.user.UserService;
import com.scoliztur.game.mafia.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.userValidator = new UserValidator();
    }

    @PostMapping("/registration")
    public String registration(@RequestBody AppUser appUser) {
        if(userValidator.validate(appUser)) {
            userService.register(appUser);
            return "Welcome " + appUser.getUsername();
        } else {
            return "Registration is not valid";
        }
    }
}
