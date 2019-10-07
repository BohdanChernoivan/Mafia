package com.scoliztur.game.mafia.logic.players;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.Civilian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerListTest {

    private PlayerList playerList;
    private Player player;

    @BeforeEach
    void setUp() {
        playerList = new PlayerList();
        player = new Civilian("Bohdan");
    }

    @Test
    void insertPlayer() {
        playerList.insertPlayer(player);
        assertEquals(playerList.getPlayerList().size(), 1);
        assertEquals(playerList.getPlayerList().get(0).getName(), "Bohdan");
    }

    @Test
    void deletePlayer() {
        playerList.insertPlayer(new Civilian("Dias"));
        playerList.insertPlayer(player);
        playerList.insertPlayer(new Civilian("Nick"));
        playerList.deletePlayer(player);
        assertEquals(playerList.getPlayerList().size(), 2);
        assertEquals(playerList.getPlayerList().get(1).getName(), "Nick");
    }

    @Test
    void getPlayerList() {
        List<Player> list = new ArrayList<>();
        assertEquals(playerList.getPlayerList(), list);
    }
}