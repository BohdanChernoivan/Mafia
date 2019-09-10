package com.scoliztur.game.mafia.logic.players.role.factory;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

public interface CreatorPlayer {
    Player createRedPlayer(RedPlayers redPlayer);
    Player createBlackPlayer(BlackPlayers blackPlayer);
}
