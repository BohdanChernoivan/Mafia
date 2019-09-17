package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.services.game.CompleteGame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mafia/changeOfDayAndNight")
public class DayController {

    private final CompleteGame game;

    public DayController(CompleteGame game) {
        this.game = game;
    }
}
