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
            victim = player;
            player.setActionNight(false);
            return player.getName() + " poisoned";
        } else if (!checkOwnActivity(day)) {
            return toString() + " is not active";
        }
        return null;
    }

    public void getAwayFromPlayer() {
        victim.setActionNight(true);
    }

    @Override
    public String toString() {
        return RedPlayers.BARMAN.getRedPlayer();
    }
}
