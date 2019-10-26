package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarmanTest {

    private Barman barman;
    private Mafia mafia;

    @BeforeEach
    void setUp() {
        barman = new Barman("Vova");
        mafia = new Mafia("Mihael");
    }

    @Test
    void poisonDay() {
        assertEquals(barman.action(mafia, true), "Now day");
    }

    @Test
    void poisonNight() {
        assertEquals(barman.action(mafia, false), mafia.getName() + " poisoned");
        barman.action(mafia, false);
        assertFalse(mafia.isActionNight());
    }

    @Test
    void poisonNotActive() {
        barman.setActionNight(false);
        assertEquals(barman.action(mafia, false), barman.toString() + " is not active");
    }

    @Test
    void getVictim() {
        barman.action(mafia, false);
        assertEquals(barman.getVictim().getName(), mafia.getName());
    }

    @Test
    void getRoleName() {
        assertEquals(barman.toString(), "Barman");
    }
}