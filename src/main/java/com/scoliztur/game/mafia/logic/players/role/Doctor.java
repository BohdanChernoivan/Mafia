package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class Doctor extends Player {

    public Doctor(String name) {
        super(name);
    }

    @Override
    public String activityNight(Player player) {
        player.setAlive(true);
        return player.getName() + " resurrected";
    }

    @Override
    public String toString() {
        return RedPlayers.DOCTOR.getNameRole();
    }
}
