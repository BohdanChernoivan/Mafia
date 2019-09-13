package com.scoliztur.game.mafia.logic.players.basic;

import com.scoliztur.game.mafia.logic.statistics.OfferedForKilling;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Player {

    private String name;
    private boolean isActionDay;
    private boolean isActionNight;
    private boolean isAlive;

    public Player() {
        this.isActionDay = true;
        this.isActionNight = true;
        this.isAlive = true;
    }

    public void pick(Player player, OfferedForKilling offered) {
        if(this.isActionDay) {
            offered.addPlayer(player);
        }
    }

    public void vote(OfferedForKilling offerPlayer) {

    }

    public abstract String getName();

}
