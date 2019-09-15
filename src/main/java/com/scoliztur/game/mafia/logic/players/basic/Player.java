package com.scoliztur.game.mafia.logic.players.basic;

import com.scoliztur.game.mafia.logic.act_game.OfferForKilling;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class Player {

    private String name;
    private boolean isActionDay;
    private boolean isActionNight;
    private boolean isAlive;

    public Player(String name) {
        this.name = name;
        this.isActionDay = true;
        this.isActionNight = true;
        this.isAlive = true;
    }

    public void pick(Player player, OfferForKilling offered) {
        if(this.isActionDay) {
            offered.addPlayer(player);
        }
    }

    public String vote(OfferForKilling offerPlayer, Player player, boolean day) {

        if(day) {
            for (Map.Entry<Player, Byte> entry : offerPlayer.getPlayersList().entrySet()) {
                if (entry.getKey().getName().equals(player.getName())) {
                    entry.setValue((byte) (entry.getValue() + 1));
                    return this.getName() + " voted for " + entry.getKey().getName();
                }
                return "There is no such player";
            }
            return "No list of offered players";
        } else
            return "Now is not day";
    }

    public boolean checkOwnActivity(boolean day) {

        return this.isActionNight() && this.isAlive() && !day;
    }
}
