package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.logic.players.role.Barman;
import com.scoliztur.game.mafia.logic.players.role.Courtesan;
import com.scoliztur.game.mafia.logic.players.role.Mafia;
import com.scoliztur.game.mafia.logic.players.role.Sheriff;
import com.scoliztur.game.mafia.logic.statistics.OfferedForKilling;
import org.springframework.stereotype.Service;

@Service
public class NewGame {

    private PrepareGame prepareGame;
    private boolean isDay = false;

    public void start() {

        prepareGame = new PrepareGame();

        Mafia mafia = new Mafia();
        Sheriff sheriff = new Sheriff();
        Barman barman = new Barman();
        Courtesan courtesan = new Courtesan();

        OfferedForKilling offeredPlayer;

        offeredPlayer = new OfferedForKilling(courtesan);

    }

    public void day() {
        isDay = true;
    }

    public void night() {
        isDay = false;
    }
}
