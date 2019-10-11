package com.scoliztur.game.mafia.logic;

import com.scoliztur.game.mafia.logic.players.basic.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OfferForKilling {

    private Map<Player, Byte> playerVoiceMap;

    public OfferForKilling() {
        playerVoiceMap = new LinkedHashMap<>();
    }

    public String addPlayer(Player player) {

        byte zeroVotes = 0;
        playerVoiceMap.put(player, zeroVotes);
        return player.getName();
    }

    public List<Player> getPlayerList() {
        List<Player> list = new ArrayList<>();

        for (Map.Entry<Player, Byte> entry : playerVoiceMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }


    public Map<Player, Byte> getPlayerVoiceMap() {
        return playerVoiceMap;
    }
}
