package com.scoliztur.game.mafia.logic.players.role;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class Sheriff extends Player {

    public Sheriff(String name) {
        super(name);
    }

    public String findMafia(Player player, boolean day) {

        if(this.isActionNight() && !day) {
            if(player.toString().equals(BlackPlayers.MAFIA.getBlackPlayer()) ||
                    player.toString().equals(BlackPlayers.DON.getBlackPlayer()) ||
                    player.toString().equals(BlackPlayers.COURTESAN.getBlackPlayer())) {
                return this.toString() + " found Mafia";
            } else return this.toString() + " not found Mafia";
        } else if (!checkOwnActivity(day)) {
            return this.toString() + " is not active";
        }
        return getName() + " not found Mafia";
    }


    @Override
    public String toString() {
        return RedPlayers.SHERIFF.getRedPlayer();
    }
}
