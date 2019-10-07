package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class Sheriff extends Player {

    public Sheriff(String name) {
        super(name);
    }

    @Override
    public String action(Player player, boolean day) {
        if(this.isActionNight() && !day) {
            if(player.toString().equals(BlackPlayers.MAFIA.getNameRole()) ||
                    player.toString().equals(BlackPlayers.DON.getNameRole()) ||
                    player.toString().equals(BlackPlayers.COURTESAN.getNameRole())) {
                return this.toString() + " found Mafia";
            } else return this.toString() + " not found Mafia";
        } else if (!checkOwnActivityAtNight()) {
            return this.toString() + " is not active";
        } else if (day) {
            return "Now day";
        }
        return this.toString() + " not found Mafia";
    }


    @Override
    public String toString() {
        return RedPlayers.SHERIFF.getNameRole();
    }
}
