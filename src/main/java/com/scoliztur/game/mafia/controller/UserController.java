package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.services.user.UserService;
import com.scoliztur.game.mafia.validator.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userValidator = new UserValidator();
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestParam("login") String login,
                                       @RequestParam("username") String username,
                                       @RequestParam("password") String password) {

        AppUser appUser = new AppUser();
        appUser.setLogin(login);
        appUser.setUsername(username);
        appUser.setPassword(password);

        if(userValidator.validate(appUser)) {
            userService.register(appUser);
            return ResponseEntity.ok().body("Welcome " + appUser.getUsername());
        } else {
            return ResponseEntity.badRequest()
                    .body("Registration is not valid:" + "\n"
                    + "Login must be between 4 and 16 characters;" + "\n"
                    + "Username must be between 3 and 12 characters;" + "\n"
                    + "Password must be over 5 characters.");
        }
    }

    @GetMapping("/view_all")
    public List<AppUser> getAll() {
        return userService.getAll();
    }

    @PostMapping("/find_by_username")
    public ResponseEntity findByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping("/find_by_id")
    public ResponseEntity findById(@RequestParam("id") UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("id") UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.ok("User deleted");
    }
}
