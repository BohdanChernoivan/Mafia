package com.scoliztur.game.mafia.logic;

import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.players.role.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MurderTest {

    private OfferForKilling offerForKilling;
    private PlayerList playerList;

    private Sheriff sheriff;
    private Don don;
    private Civilian civilian;


    @BeforeEach
    void setUp() {
        civilian = new Civilian("Lola");
        sheriff = new Sheriff("Big");
        don = new Don("Dick");

        playerList = new PlayerList();

        playerList.insertPlayer(civilian);
        playerList.insertPlayer(sheriff);
        playerList.insertPlayer(don);
        playerList.insertPlayer(new Barman("Large"));
        playerList.insertPlayer(new Doctor("Wet"));

        offerForKilling = new OfferForKilling();
    }

    @Test
    void killFromSelected() {
        offerForKilling.addPlayer(civilian);
        Murder.killFromSelected(offerForKilling, playerList);
        assertEquals(playerList.getPlayerList().size(), 4);
    }
}