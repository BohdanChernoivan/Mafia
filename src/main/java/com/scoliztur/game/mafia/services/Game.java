package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.logic.act_game.Murder;
import com.scoliztur.game.mafia.logic.act_game.OfferForKilling;
import com.scoliztur.game.mafia.logic.game.CompleteGame;
import org.springframework.stereotype.Service;


@Service
public class Game {

    public CompleteGame completeGame = new CompleteGame();

    public void newListForMaffiozi() {
        completeGame.listForMafia = new OfferForKilling();
    }

    public void newListForCivilian() {
        completeGame.listForCivilian = new OfferForKilling();
    }

    public void murderDay() {
        Murder.killFromSelected(completeGame.listForCivilian, completeGame.playerList);
    }

    public void murderNightForMaffiozi() {
        Murder.killFromSelected(completeGame.listForMafia, completeGame.playerList);
    }

}
