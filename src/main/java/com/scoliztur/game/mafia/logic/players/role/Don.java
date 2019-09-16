package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class Don extends Mafia {

    public Don(String name) {
        super(name);
    }

    public String findSheriff(Player player, boolean day) {

        if (this.isActionNight() && !day) {
            if (player.toString().equals(RedPlayers.SHERIFF.getNameRole())) {
                return this.toString() + " found Sheriff";
            } else return this.toString() + " not found Sheriff";
        } else if (!checkOwnActivity()) {
            return this.toString() + " is not active";
        } else if (day) {
            return "Now day";
        }
        return null;
    }

    @Override
    public String toString() {
        return BlackPlayers.DON.getNameRole();
    }
}
