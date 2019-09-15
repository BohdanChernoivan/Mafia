package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.logic.Roles;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Game {

    protected Roles roles = new Roles();

    public void addRole(Player player) {
        roles.insert(player);
    }

    private boolean isDay = false;
    private List<Player> playerList;

    public void start() {

        playerList = roles.getPlayerList();
//
//        Mafia mafia = new Mafia();
//        Sheriff sheriff = new Sheriff();
//        Barman barman = new Barman();
//        Courtesan courtesan = new Courtesan();
//
//        OfferForKilling offeredPlayer;
//
//        offeredPlayer = new OfferForKilling(courtesan);

    }

    public void day() {
        isDay = true;
    }

    public void night() {
        isDay = false;
    }
}
