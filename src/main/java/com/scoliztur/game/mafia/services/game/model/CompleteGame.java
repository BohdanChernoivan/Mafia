package com.scoliztur.game.mafia.services.game.model;

public interface CompleteGame {

    void day();
    void night();
    String pickPlayerSelectionOrder(String playerName, int numberPlayer);
    String vote(String playerName, int numberPlayer);
    String actionPlayer(String playerName, int numberPlayer);
}
