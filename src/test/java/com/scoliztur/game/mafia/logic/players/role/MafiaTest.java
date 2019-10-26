package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MafiaTest {

    private Mafia mafia0;
    private Mafia mafia1;
    private Civilian civilian0;
    private Civilian civilian1;
    private OfferForKilling offer;

    @BeforeEach
    void setUp() {
        mafia0 = new Mafia("Back");
        mafia1 = new Mafia("Front");
        civilian0 = new Civilian("No");
        civilian1 = new Civilian("Yes");
        offer = new OfferForKilling();

        mafia0.setOffer(offer);
        mafia1.setOffer(offer);
    }

    @Test
    void offerKillNight() {
        assertEquals(mafia0.action(civilian0, false),civilian0.getName() + " want to kill Mafia");
    }

    @Test
    void offerKillIsNotActive() {
        mafia1.setActionNight(false);
        assertEquals(mafia1.action(civilian1, false), mafia1.toString() + " is not active");
    }

    @Test
    void offerKillDay() {
        assertEquals(mafia0.action(civilian0, true), "Now day");
    }

    @Test
    void getRoleName() {
        assertEquals(mafia1.toString(), "Mafia");
    }
}