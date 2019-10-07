package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CivilianTest {

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
    void checkOwnActivity() {
        assertTrue(civilian.checkOwnActivityAtNight());
    }

    @Test
    void checkOwnActivityFalseAlive() {
        civilian.setAlive(false);
        assertFalse(civilian.checkOwnActivityAtNight());
    }

    @Test
    void checkOwnActivityNotActiveNight() {
        civilian.setActionNight(false);
        assertFalse(civilian.checkOwnActivityAtNight());
    }

    @Test
    void checkOwnActivityNotActiveDay() {
        civilian.setActionDay(false);
        assertTrue(civilian.checkOwnActivityAtNight());
    }

    @Test
    void getRoleName() {
        assertEquals(civilian.toString(), RedPlayers.CIVILIAN.getNameRole());
    }
}