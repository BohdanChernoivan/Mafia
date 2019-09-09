package com.scoliztur.game.mafia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.scoliztur.game.mafia.services.PrepareGame.GAME_LINK;

@RestController()
@RequestMapping(GAME_LINK + "/game")
public class GameController {

}
