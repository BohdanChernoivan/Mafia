package com.scoliztur.game.mafia.logic;

import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.players.basic.Player;

import java.util.Map;

public class Murder {

    public String killFromSelected(OfferForKilling playerMap, PlayerList playerList) {
        Map.Entry<Player, Byte> mapForKilledPlayer = null;

        for (Map.Entry<Player, Byte> entry : playerMap.getPlayerVoiceMap().entrySet()) {
            if(mapForKilledPlayer == null || entry.getValue().compareTo(mapForKilledPlayer.getValue()) > 0) {
                mapForKilledPlayer = entry;
            }
        }

        if (mapForKilledPlayer != null) {
            mapForKilledPlayer.getKey().setAlive(false);
            playerList.deletePlayer(mapForKilledPlayer.getKey());
            return mapForKilledPlayer.getKey().getName() + " was killed";
        }

        return "No one was killed";
    }

}
