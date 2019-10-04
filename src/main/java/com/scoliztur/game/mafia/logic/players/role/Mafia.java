package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.OfferForKilling;

public class Mafia extends Player {

    public Mafia(String name) {
        super(name);
    }

    @Override
    public String action(Player player, boolean isActionDay) {
        return null;
    }

    @Override
    public String additionalAction(Player player, boolean day, OfferForKilling offerForKilling) {
        if (this.isActionNight() && !day) {
            offerForKilling.addPlayer(player);
            return player.getName() + " want to kill Mafia";
        } else if (!checkOwnActivity()) {
            return toString() + " is not active";
        } else if (day) {
            return "Now day";
        }
        return null;
    }

    @Override
    public String toString() {
        return BlackPlayers.MAFIA.getNameRole();
    }
}
