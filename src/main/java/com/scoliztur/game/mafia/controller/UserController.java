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
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = new UserValidator();
    }

    @GetMapping("/sign_up")
    public String registration(Model model) {
        model.addAttribute("AppUser", new AppUser());

        return "registration";
    }

    @PostMapping("/sign_up")
    public String registration(@RequestBody AppUser appUser, BindingResult bindingResult) {

        userValidator.validate(appUser, bindingResult);

        if(bindingResult.hasErrors()) {
            return "sign_up";
        }

        userService.register(appUser);

        securityService.autoLogin(appUser.getUsername(), appUser.getConfirmPassword());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {

        if(error!= null) {
            model.addAttribute("error", "Login or Username or Password is incorrect.");
        }

        if(logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @GetMapping(value = {"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
