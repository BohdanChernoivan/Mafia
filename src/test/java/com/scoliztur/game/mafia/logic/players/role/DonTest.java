package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DonTest {

    private Sheriff sheriff;
    private Don don;
    private Civilian civilian;

    @BeforeEach
    void setUp() {
        sheriff = new Sheriff("Nick");
        don = new Don("Dias");
        civilian = new Civilian("Lola");
    }

    @Test
    void findSheriff() {
        assertEquals(don.findSheriff(sheriff, false), don.toString() + " found Sheriff");
    }

    @Test
    void findMafiaDay() {
        assertEquals(don.findSheriff(sheriff, true), "Now day");
    }

    @Test
    void findMafiaBeingNotActiveNight() {
        don.setActionNight(false);
        assertEquals(don.findSheriff(sheriff, false), don.toString() + " is not active");
    }

    @Test
    void foundCivilian() {
        assertEquals(don.findSheriff(civilian, false), don.toString() + " not found Sheriff");
    }

    @Test
    void getName() {
        assertEquals(don.toString(), BlackPlayers.DON.getNameRole());
    }
}