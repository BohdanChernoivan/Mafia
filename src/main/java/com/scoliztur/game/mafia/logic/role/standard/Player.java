package com.scoliztur.game.mafia.logic.role.standard;

import com.scoliztur.game.mafia.logic.statistics.OfferedForKilling;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Player {

    private boolean isAlive;

    public Player() {
        this.isAlive = true;
    }

    public void vote(OfferedForKilling offerPlayer) {
        offerPlayer.takeVote();
    }
}
