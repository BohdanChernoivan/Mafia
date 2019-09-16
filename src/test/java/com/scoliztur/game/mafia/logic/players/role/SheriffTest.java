package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SheriffTest {

    private Sheriff sheriff;
    private Mafia mafia;
    private Civilian civilian;

    @BeforeEach
    void setUp() {
        sheriff = new Sheriff("Nick");
        mafia = new Mafia("Dias");
        civilian = new Civilian("Lola");
    }

    @Test
    void foundMafia() {
        assertEquals(sheriff.findMafia(mafia, false), sheriff.toString() + " found Mafia");
    }

    @Test
    void findMafiaDay() {
        assertEquals(sheriff.findMafia(mafia, true), sheriff.toString() +" is not active");
    }

    @Test
    void foundCivilian() {
        assertEquals(sheriff.findMafia(civilian, false), sheriff.toString() +" not found Mafia");
    }

    @Test
    void getName() {
        assertEquals(sheriff.toString(), RedPlayers.SHERIFF.getNameRole());
    }
}