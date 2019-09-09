package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.logic.role.Mafia;
import com.scoliztur.game.mafia.services.PrepareGame;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RolesController {

    private final PrepareGame prepareGame;
    private final Room room;


    public RolesController(PrepareGame prepareGame, Room room) {
        this.prepareGame = prepareGame;
        this.room = room;
    }

    @GetMapping("/add/Mafia")
    public String addMafia() {

        Mafia mafia = new Mafia();
        prepareGame.addRole(mafia);

        return "Added " + mafia.getName();
    }
}
