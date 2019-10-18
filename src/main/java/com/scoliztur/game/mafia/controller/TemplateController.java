package com.scoliztur.game.mafia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/registration")
    public String registration() {
        return "forward:/registration.html";
    }

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    @GetMapping("/room")
    public String room() {
        return "forward:/room.html";
    }

    @GetMapping("/game")
    public String game() {
        return "forward:/game.html";
    }
}
