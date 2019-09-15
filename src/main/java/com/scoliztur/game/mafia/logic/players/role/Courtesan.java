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
            victim = player;
            player.setActionDay(false);
            return player.getName() + " confused";
        } else if(!checkOwnActivity(day)) {
            return toString() + " is not active";
        }
        return null;
    }

    public void getAwayFromPlayer() {
        victim.setActionDay(true);
    }

    @Override
    public String toString() {
        return BlackPlayers.COURTESAN.getBlackPlayer();
    }
}
