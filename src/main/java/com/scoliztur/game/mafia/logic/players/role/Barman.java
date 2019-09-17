package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import lombok.Getter;

@Getter
public class Barman extends Player {

    private Player victim;
    public Barman(String name) {
        super(name);
    }

    public String poison(Player player, boolean day) {
        if(this.isActionNight() && !day) {
            getAwayFromPlayer();
            victim = player;
            player.setActionNight(false);
            return player.getName() + " poisoned";
        } else if (!checkOwnActivity()) {
            getAwayFromPlayer();
            return toString() + " is not active";
        } else if (day) {
            return "Now day";
        }
        return null;
    }

    private void getAwayFromPlayer() {
        if(victim != null) {
            victim.setActionNight(true);
        }
    }

    @Override
    public String toString() {
        return RedPlayers.BARMAN.getNameRole();
    }
}
