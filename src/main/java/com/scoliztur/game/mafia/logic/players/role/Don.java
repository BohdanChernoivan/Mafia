package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;

public class Don extends Mafia {

    public String findSheriff(Player player, boolean day) {

        if(this.isActionNight() && !day) {
            if(player.getName().equals(new Sheriff().getName())) {
                return getName() + " found the Sheriff";
            }
            return getName() + " not found the Sheriff";
        }
        return getName() + " not found the Sheriff";

    }

    @Override
    public String getName() {
        return "Don";
    }
}
