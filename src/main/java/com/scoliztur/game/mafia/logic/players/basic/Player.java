package com.scoliztur.game.mafia.logic.players.basic;

import com.scoliztur.game.mafia.logic.OfferForKilling;
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

    public void pick(OfferForKilling offered, Player player, boolean day) {
        if(day) {
            if (this.isActionDay) {
                offered.addPlayer(player);
            }
        }
    }

    public abstract String action(Player player, boolean isActionDay);

    public String vote(OfferForKilling offerPlayer, Player player, boolean day) {
        if(day) {
            for (Map.Entry<Player, Byte> entry : offerPlayer.getPlayerVoiceMap().entrySet()) {
                if (entry.getKey() == player) {
                    entry.setValue((byte) (entry.getValue() + 1));
                    return this.getName() + " voted for " + entry.getKey().getName();
                }
            }
            return "There is no such player";
        } else
            return "Now is not day";
    }

    public boolean checkOwnActivity() {

        return this.isActionNight() && this.isAlive();
    }
}
