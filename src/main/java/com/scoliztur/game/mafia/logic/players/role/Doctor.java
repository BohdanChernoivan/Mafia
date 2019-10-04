package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class Doctor extends Player {

    public Doctor(String name) {
        super(name);
    }

    @Override
    public String action(Player player, boolean day) {
        if (this.isActionNight() && !day) {
            player.setAlive(true);
            return player.getName() + " resurrected";
        } else if (!checkOwnActivity()) {
            return toString() + " is not active";
        } else if (day) {
            return "Now day";
        }
        return null;
    }

    @Override
    public String toString() {
        return RedPlayers.DOCTOR.getNameRole();
    }
}
