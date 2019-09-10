package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;

public class Doctor extends Player {

    public void resurrect(Player player, boolean day) {

        if(this.isActionNight() && !day) {
            player.setAlive(true);
        }

    }

    @Override
    public String getName() {
        return "Doctor";
    }
}
