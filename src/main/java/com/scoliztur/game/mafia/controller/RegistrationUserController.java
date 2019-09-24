package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.filters.validator.UserValidator;
import com.scoliztur.game.mafia.security.UserPrincipal;
import com.scoliztur.game.mafia.services.user.CustomUserDetailsService;
import com.scoliztur.game.mafia.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class RegistrationUserController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public RegistrationUserController(UserService userService, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.userValidator = new UserValidator();
    }


    @PostMapping("sign_up")
    public String userRegistration(@RequestBody AppUser appUser) {


        if(userValidator.validate(appUser)) {
                appUser = userService.register(appUser);

        } else return "Not valid request body";

        return appUser.getUsername();
    }
}
