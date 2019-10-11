package com.scoliztur.game.mafia.logic.players.role.type;

public enum RedPlayers {

    SHERIFF("Sheriff"),
    BARMAN("Barman"),
    CIVILIAN("Civilian"),
    DOCTOR("Doctor");

    private String nameRole;

    RedPlayers(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }
}
