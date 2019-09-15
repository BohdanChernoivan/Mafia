package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class Civilian extends Player {

    public Civilian(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return RedPlayers.CIVILIAN.getRedPlayer();
    }
}
