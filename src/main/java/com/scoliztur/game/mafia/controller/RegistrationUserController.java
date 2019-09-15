package com.scoliztur.game.mafia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mafia/registration")
public class RegistrationUserController {

    @GetMapping
    public String user() {
        return "Hello new USER!";
    }
}
