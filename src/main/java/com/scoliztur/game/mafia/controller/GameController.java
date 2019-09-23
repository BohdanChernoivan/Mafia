package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.logic.players.role.factory.RolePlayerFactory;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
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
    private final RolePlayerFactory rolePlayerFactory;

    @Autowired
    public GameController(CompleteGame game) {
        this.game = game;
        rolePlayerFactory = new RolePlayerFactory();
    }

    @GetMapping
    public String getRoles(Principal principal) {
        return principal.getName();
//        game.playerList.insertPlayer(
//                rolePlayerFactory
//                .createBlackPlayer(BlackPlayers.DON, "Pet")
//        );
    }
}
