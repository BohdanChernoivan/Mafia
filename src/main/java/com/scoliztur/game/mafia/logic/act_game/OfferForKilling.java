package com.scoliztur.game.mafia.logic.act_game;

import com.scoliztur.game.mafia.logic.players.basic.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class OfferForKilling {

    private Map<Player, Byte> playersList;

    public OfferForKilling() {
        playersList = new LinkedHashMap<>();
    }

    public void addPlayer(Player player) {

        byte zeroVotes = 0;
        playersList.put(player, zeroVotes);
    }


    public Map<Player, Byte> getPlayersList() {
        return playersList;
    }
}
