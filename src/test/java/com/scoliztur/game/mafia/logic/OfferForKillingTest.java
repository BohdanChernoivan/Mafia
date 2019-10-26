package com.scoliztur.game.mafia.logic;

import com.scoliztur.game.mafia.logic.players.role.Civilian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferForKillingTest {

    private OfferForKilling offer;
    private Civilian civilian1;
    private Civilian civilian2;
    private Civilian civilian3;

    @BeforeEach
    void setUp() {
        offer = new OfferForKilling();
        civilian1 = new Civilian("Bob");
        civilian2 = new Civilian("Gob");
        civilian3 = new Civilian("Rob");
    }

    @Test
    void addPlayer() {
        assertEquals(offer.addPlayer(civilian1), civilian1.getName());
        assertEquals(offer.addPlayer(civilian2), civilian2.getName());
        civilian3.setName("Nick");
        assertEquals(offer.addPlayer(civilian3), "Nick");
    }

    @Test
    void getPlayerList() {
        offer.addPlayer(civilian1);
        offer.addPlayer(civilian2);
        offer.addPlayer(civilian3);
        assertEquals(offer.getPlayerList().size(), 3);
    }


}