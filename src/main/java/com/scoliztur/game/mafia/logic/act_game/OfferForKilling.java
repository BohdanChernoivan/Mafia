package com.scoliztur.game.mafia.logic.act_game;

import com.scoliztur.game.mafia.logic.players.basic.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OfferForKilling {

    private Map<Player, Byte> playerByteMap;

    public OfferForKilling() {
        playerByteMap = new LinkedHashMap<>();
    }

    public void addPlayer(Player player) {

        byte zeroVotes = 0;
        playerByteMap.put(player, zeroVotes);
    }

    public List<Player> getPlayerList() {

        List<Player> list = new ArrayList<>();

        for (Map.Entry<Player, Byte> entry : playerByteMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }


    public Map<Player, Byte> getPlayerByteMap() {
        return playerByteMap;
    }
}
