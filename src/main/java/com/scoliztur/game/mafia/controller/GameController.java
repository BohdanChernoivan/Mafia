package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.services.factory.PlayerRoleBindingService;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/mafia/game/")
public class GameController {

    private final CompleteGame game;
    private final PlayerRoleBindingService playerRoleBindingService;

    @Autowired
    public GameController(CompleteGame game) {
        this.game = game;
        playerRoleBindingService = new PlayerRoleBindingService();
    }

    @GetMapping
    public String getRoles(Principal principal) {
        return principal.getName();
//        game.playerList.insertPlayer(
//                playerRoleBindingService
//                .createBlackPlayer(BlackPlayers.DON, "Pet")
//        );
    }
}
