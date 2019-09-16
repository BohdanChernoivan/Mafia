package com.scoliztur.game.mafia.logic.game;

import com.scoliztur.game.mafia.logic.PlayerList;
import com.scoliztur.game.mafia.logic.act_game.OfferForKilling;

public class CompleteGame {

    public PlayerList playerList = new PlayerList();
    public OfferForKilling listForMafia;
    public OfferForKilling listForCivilian;
    private boolean isDay = true;

    public boolean isDay() {
        return isDay;
    }

    public void day() {
        isDay = true;
    }
    public void night() {
        isDay = false;
    }
}
