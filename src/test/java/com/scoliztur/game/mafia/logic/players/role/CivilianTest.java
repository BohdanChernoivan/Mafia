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
    void toString1() {
        assertEquals(civilian.toString(), RedPlayers.CIVILIAN.getRedPlayer());
    }

    @Test
    void checkOwnActivity() {
        assertTrue(civilian.checkOwnActivity(false));
    }

    @Test
    void checkOwnActivityDay() {
        assertFalse(civilian.checkOwnActivity(true));
    }

    @Test
    void checkOwnActivityFalseAlive() {
        civilian.setAlive(false);
        assertFalse(civilian.checkOwnActivity(false));
    }
}