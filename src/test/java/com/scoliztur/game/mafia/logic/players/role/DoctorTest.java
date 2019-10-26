package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    private Doctor doctor;
    private Sheriff sheriff;

    @BeforeEach
    void setUp() {
        doctor = new Doctor("Glue");
        sheriff = new Sheriff("Poster");
    }

    @Test
    void resurrectPlayerAlive() {
        assertEquals(doctor.action(sheriff, false), sheriff.getName() + " resurrected");
    }

    @Test
    void resurrectPlayerNotAlive() {
        sheriff.setAlive(false);
        assertEquals(doctor.action(sheriff, false), sheriff.getName() + " resurrected");
    }

    @Test
    void resurrectNotAliveBoolean() {
        sheriff.setAlive(false);
        doctor.action(sheriff, false);
        assertTrue(sheriff.isAlive());
    }

    @Test
    void getRoleName() {
        assertEquals(doctor.toString(), "Doctor");
    }
}