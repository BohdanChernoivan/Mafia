package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.services.user.model.SecurityService;
import com.scoliztur.game.mafia.validator.UserValidator;
import com.scoliztur.game.mafia.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/sign_up")
    public String registration(@RequestBody AppUser appUser, BindingResult bindingResult) {

        userValidator.validate(appUser, bindingResult);

        if(bindingResult.hasErrors()) {
            return "sign_up";
        }

        userService.register(appUser);

        return "welcome";
    }
    /*redirect:/*/


//    @GetMapping(value = {"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
}
