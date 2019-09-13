package com.scoliztur.game.mafia.logic.players.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SheriffTest {

    Sheriff sheriff;
    Mafia mafia;
    Civilian civilian;

    @BeforeEach
    void setUp() {
        sheriff = new Sheriff();
        mafia = new Mafia();
        civilian = new Civilian();
    }

    @Test
    void findMafia() {
        assertEquals(sheriff.findMafia(mafia, false), sheriff.getName() + " found Mafia");

    }

    @Test
    void getName() {

    }
}