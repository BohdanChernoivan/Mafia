package com.scoliztur.game.mafia.logic.players.basic;

import com.scoliztur.game.mafia.logic.OfferForKilling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Player enemyPlayer;
    private OfferForKilling offer;

    @BeforeEach
    void setUp() {
        player = new Player("Player") {
            @Override
            public String action(Player player, boolean isActionDay) {
                return null;
            }
        };

        enemyPlayer = new Player("Enemy player") {
            @Override
            public String action(Player player, boolean isActionDay) {
                return null;
            }
        };

        offer = new OfferForKilling();
        offer.addPlayer(enemyPlayer);
    }

    @Test
    void checkPlayerName() {
        assertEquals(player.getName(), "Player");
        assertEquals(enemyPlayer.getName(), "Enemy player");
    }

    @Test
    void checkPlayerVoteIsNotActive() {
        player.setActionDay(false);
        assertEquals(player.vote(offer, enemyPlayer, true), "You not active");
    }

    @Test
    void checkPlayerVoteAtNight() {
        player.setActionDay(true);
        assertEquals(player.vote(offer, enemyPlayer, false), "Now is not day");
    }

    @Test
    void checkPlayerVoteNoneEnemyPlayer() {
        assertEquals(player.vote(new OfferForKilling(), enemyPlayer, true), "There is no such player");
    }

    @Test
    void checkOwnActivityAtNight() {
        assertTrue(player.checkOwnActivityAtNight());
    }

    @Test
    void checkOwnActivityAtNightDied() {
        player.setAlive(false);
        assertFalse(player.checkOwnActivityAtNight());
    }

    @Test
    void checkOwnActivityAtNightNotActive() {
        player.setActionNight(false);
        assertFalse(player.checkOwnActivityAtNight());
    }

    @Test
    void checkPickNotActive() {
        player.setActionDay(false);
        assertEquals(player.pick(offer, enemyPlayer, true), "You not active");
    }

    @Test
    void checkPickNight() {
        assertEquals(player.pick(offer, enemyPlayer, false), "Now night");
    }
}