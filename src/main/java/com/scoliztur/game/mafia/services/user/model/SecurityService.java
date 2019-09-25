package com.scoliztur.game.mafia.services.user.model;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String login, String password);
}
