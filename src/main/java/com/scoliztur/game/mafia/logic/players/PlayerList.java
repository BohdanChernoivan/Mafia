package com.scoliztur.game.mafia.logic.players;

import com.scoliztur.game.mafia.logic.players.basic.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

    private ArrayList<Player> playerList;

    public PlayerList() {
        playerList = new ArrayList<>();
    }

    public void insertPlayer(Player player) {
        playerList.add(player);
    }

    public void deletePlayer(Player player) {
        playerList.remove(player);
        playerList.trimToSize();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

}
