package com.scoliztur.game.mafia.logic.players.role.type;

public enum BlackPlayers {

    DON("Don"),
    MAFIA("Mafia"),
    COURTESAN("Courtesan");

    private String nameRole;

    BlackPlayers(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }
}
