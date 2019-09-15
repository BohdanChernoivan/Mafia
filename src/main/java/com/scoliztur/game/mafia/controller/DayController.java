package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.services.Game;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mafia/change")
public class DayController {

    private final Game game;

    public DayController(Game game) {
        this.game = game;
    }
}
