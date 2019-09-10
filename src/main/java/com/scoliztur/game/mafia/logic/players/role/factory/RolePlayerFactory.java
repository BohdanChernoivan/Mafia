package com.scoliztur.game.mafia.logic.players.role.factory;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.*;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public class RolePlayerFactory implements CreatorPlayer{


    @Override
    public Player createRedPlayer(RedPlayers redPlayer) {

        switch (redPlayer) {
            case SHERIFF:
                return new Sheriff();
            case BARMAN:
                return new Barman();
            case CIVILIAN:
                return new Civilian();
            case DOCTOR:
                return new Doctor();
                default:
                    return null;
        }
    }

    @Override
    public Player createBlackPlayer(BlackPlayers blackPlayer) {

        switch (blackPlayer) {
            case DON:
                return new Don();
            case MAFIA:
                return new Mafia();
            case COURTESAN:
                return new Courtesan();
                default:
                    return null;
        }
    }
}
