package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;

public class Sheriff extends Player {

    public String findMafia(Player player, boolean day) {

        if(this.isActionNight() && !day) {
            if(player.getName().equals(new Mafia().getName())) {
                return getName() + " found Mafia";
            }
            return getName() + " not found Mafia";
        }
        return getName() + " not found Mafia";

    }

    @Override
    public String getName() {
        return "Sheriff";
    }
}
