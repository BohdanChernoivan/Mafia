package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.OfferForKilling;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mafia extends Player {

    private OfferForKilling offer;

    public Mafia(String name) {
        super(name);
    }

    @Override
    public String activityNight(Player player) {
        offer.addPlayer(player);
        return player.getName() + " want to kill Mafia";
    }

    @Override
    public String toString() {
        return BlackPlayers.MAFIA.getNameRole();
    }
}
