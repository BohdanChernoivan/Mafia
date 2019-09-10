package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;

public class Barman extends Player {

    public void poison(Player player, boolean day) {

        if(this.isActionNight() && !day) {
            player.setActionNight(false);
        }

    }

    @Override
    public String getName() {
        return "Barman";
    }
}
