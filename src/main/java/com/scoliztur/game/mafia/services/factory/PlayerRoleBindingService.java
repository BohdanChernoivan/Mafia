package com.scoliztur.game.mafia.services.factory;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.*;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import com.scoliztur.game.mafia.services.factory.model.CreatorPlayer;
import org.springframework.stereotype.Service;

@Service
public class PlayerRoleBindingService implements CreatorPlayer {

    @Override
    public Player createRedPlayer(RedPlayers redPlayer, String name) {
        switch (redPlayer) {
            case SHERIFF:
                return new Sheriff(name);
            case BARMAN:
                return new Barman(name);
            case CIVILIAN:
                return new Civilian(name);
            case DOCTOR:
                return new Doctor(name);
                default:
                    return null;
        }
    }

    @Override
    public Player createBlackPlayer(BlackPlayers blackPlayer, String name) {
        switch (blackPlayer) {
            case DON:
                return new Don(name);
            case MAFIA:
                return new Mafia(name);
            case COURTESAN:
                return new Courtesan(name);
                default:
                    return null;
        }
    }
}
