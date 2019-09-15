package com.scoliztur.game.mafia.logic.players.role.type;

public enum RedPlayers {

    SHERIFF("Sheriff"),
    BARMAN("Barman"),
    CIVILIAN("Civilian"),
    DOCTOR("Doctor");

    private String redPlayer;

    RedPlayers(String redPlayer) {
        this.redPlayer = redPlayer;
    }

    public String getRedPlayer() {
        return redPlayer;
    }
}
