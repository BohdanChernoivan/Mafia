package com.scoliztur.game.mafia.logic.act_game;

import com.scoliztur.game.mafia.logic.players.basic.Player;

import java.util.Map;

public class Murder {

    public String killFromSelected(Map<Player, Byte> players) {

        Map.Entry<Player, Byte> maxBytePlayer = null;

        for (Map.Entry<Player, Byte> entry : players.entrySet()) {
            if(maxBytePlayer == null || entry.getValue().compareTo(maxBytePlayer.getValue()) > 0) {
                maxBytePlayer = entry;
            }
        }

        if (maxBytePlayer != null) {
            maxBytePlayer.getKey().setAlive(false);
            return maxBytePlayer.getKey().getName() + " was killed";
        }

        return "No one was killed";
    }

}
