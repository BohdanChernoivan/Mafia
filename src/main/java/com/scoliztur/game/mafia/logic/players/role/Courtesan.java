package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import lombok.Getter;

@Getter
public class Courtesan extends Player {

    private Player victim;
    public Courtesan(String name) {
        super(name);
    }

    public String confuse(Player player, boolean day) {

        if(this.isActionNight() && !day) {
            getAwayFromPlayer();
            victim = player;
            player.setActionDay(false);
            return player.getName() + " confused";
        } else if(!checkOwnActivity()) {
            getAwayFromPlayer();
            return toString() + " is not active";
        } else if (day) {
            return "Now day";
        }
        return null;
    }

    private void getAwayFromPlayer() {
        if(victim != null) {
            victim.setActionDay(true);
        }
    }

    @Override
    public String toString() {
        return BlackPlayers.COURTESAN.getNameRole();
    }
}
