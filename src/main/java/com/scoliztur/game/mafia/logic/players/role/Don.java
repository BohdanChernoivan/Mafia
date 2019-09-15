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
            if (player.toString().equals(RedPlayers.SHERIFF.getRedPlayer())) {
                return this.toString() + " found Sheriff";
            } else return this.toString() + " not found Sheriff";
        } else if (!checkOwnActivity(day)) {
            return this.toString() + " is not active";
        }
        return null;
    }

    @Override
    public String toString() {
        return BlackPlayers.DON.getBlackPlayer();
    }
}
