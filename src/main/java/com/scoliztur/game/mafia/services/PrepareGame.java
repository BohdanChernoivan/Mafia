package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.logic.Roles;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import org.springframework.stereotype.Service;

@Service
public class PrepareGame {

    public static final String GAME_LINK = "/mafia";

    protected Roles roles = new Roles();

    public void addRole(Player player) {
        roles.insert(player);
    }

}
