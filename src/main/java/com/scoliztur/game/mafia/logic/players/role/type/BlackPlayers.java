package com.scoliztur.game.mafia.logic.players.role.type;

public enum BlackPlayers {

    DON("Don"),
    MAFIA("Mafia"),
    COURTESAN("Courtesan");

    private String blackPlayer;

    BlackPlayers(String blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }
}
