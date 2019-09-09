package com.scoliztur.game.mafia.logic;


import com.scoliztur.game.mafia.logic.role.standard.Player;

import java.util.ArrayList;
import java.util.List;

public class Roles {

    private List<Player> playerList;

    public Roles() {
        playerList = new ArrayList<>();
    }

    public void insert(Player player) {
        playerList.add(player);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
