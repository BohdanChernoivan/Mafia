package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;

public class Courtesan extends Player {

    public void occupy(Player player, boolean day) {

        if(this.isActionNight() && !day) {
            player.setActionDay(false);
        }

    }

    @Override
    public String getName() {
        return "Courtesan";
    }
}
