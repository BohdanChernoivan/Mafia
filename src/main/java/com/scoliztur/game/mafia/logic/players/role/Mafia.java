package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.act_game.OfferForKilling;

public class Mafia extends Player {

    public Mafia(String name) {
        super(name);
    }

    public String offerKillNight(Player player, boolean day, OfferForKilling offerForKilling) {

        if (this.isActionNight() && !day) {
            offerForKilling.addPlayer(player);
            return player.getName() + " want to kill Mafia";
        } else if (!checkOwnActivity(day)) {
            return toString() + " is not active";
        }
        return null;
    }

    @Override
    public String toString() {
        return BlackPlayers.MAFIA.getBlackPlayer();
    }
}
