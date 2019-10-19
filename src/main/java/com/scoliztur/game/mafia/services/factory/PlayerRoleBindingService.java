package com.scoliztur.game.mafia.services.factory;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.*;
import com.scoliztur.game.mafia.services.factory.model.CreatorPlayer;
import org.springframework.stereotype.Service;

@Service
public class PlayerRoleBindingService implements CreatorPlayer {

    @Override
    public Player createPlayer(String nameRole, String name) {
        switch (nameRole) {
            case "Sheriff":
                return new Sheriff(name);
            case "Barman":
                return new Barman(name);
            case "Civilian":
                return new Civilian(name);
            case "Doctor":
                return new Doctor(name);
            case "Don":
                return new Don(name);
            case "Mafia":
                return new Mafia(name);
            case "Courtesan":
                return new Courtesan(name);
                default:
                    return null;
        }
    }
}
