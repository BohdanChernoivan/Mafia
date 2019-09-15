package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class Doctor extends Player {

    public Doctor(String name) {
        super(name);
    }

    public String resurrect(Player player, boolean day) {

        if (this.isActionNight() && !day) {
            player.setAlive(true);
            return player.getName() + " resurrected";
        } else if (!checkOwnActivity(day)) {
            return toString() + " is not active";
        }
        return null;
    }

    @Override
    public String toString() {
        return RedPlayers.DOCTOR.getRedPlayer();
    }
}
