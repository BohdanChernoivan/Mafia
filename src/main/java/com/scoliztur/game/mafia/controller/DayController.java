package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.services.PrepareGame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PrepareGame.GAME_LINK + "/change")
public class DayController {
}