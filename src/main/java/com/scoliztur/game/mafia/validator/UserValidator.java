package com.scoliztur.game.mafia.validator;

public class UserValidator {

    private static final String LOGIN = "^[a-zA-Z0-9_-]{4,16}$";

    private static final String USERNAME = "^[a-zA-Z0-9]{3,12}$";

    private static final String PASSWORD = "^[a-zA-Z0-9]{5,12}$";


    public boolean validateLogin(String userLogin) {

        return !userLogin.matches(LOGIN);
    }

    public boolean validateUsername(String userUsername) {

        return !userUsername.matches(USERNAME);
    }

    public boolean validatePassword(String userPassword) {

        return !userPassword.matches(PASSWORD);
    }
}
