package com.scoliztur.game.mafia.services.game;

import com.scoliztur.game.mafia.logic.Murder;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import com.scoliztur.game.mafia.services.game.model.ChangeOfDayAndNight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class CompleteGame implements ChangeOfDayAndNight {

    public List<String> nameOfList;
    public List<Player> listOfRole;
    public PlayerList playerList = new PlayerList();
    public OfferForKilling listForMafia;
    public OfferForKilling listForCivilian;
    private boolean isDay;

    @Override
    public void day() {
        isDay = true;
    }

    @Override
    public void night() {
        isDay = false;
    }

    @Override
    public boolean isDay() {
        return isDay;
    }

    public void newPlayerList() {
        playerList = new PlayerList();
    }

    public void newListForMaffiozi() {
        listForMafia = new OfferForKilling();
    }

    public void newListForCivilian() {
        listForCivilian = new OfferForKilling();
    }

    public void murderDay() {
        Murder.killFromSelected(listForCivilian, playerList);
    }

    public void murderNightForMaffiozi() {
        Murder.killFromSelected(listForMafia, playerList);
    }

}
