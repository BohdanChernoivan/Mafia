package com.scoliztur.game.mafia.validator;

import com.scoliztur.game.mafia.entity.AppUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserValidator {

    private static final String LOGIN = "^[a-zA-Z0-9_-]{4,16}$";

    private static final String USERNAME = "^[a-zA-Z0-9]{3,12}$";

    private static final String PASSWORD = "^[a-zA-Z0-9]{5,12}$";


    public boolean validate(AppUser appUser) {

        if (!appUser.getLogin().matches(LOGIN)) {
            log.warn("Login must be between 4 and 16 characters. {}", appUser.getLogin());
            return false;
        }

        if (!appUser.getUsername().matches(USERNAME)) {
            log.warn("Username must be between 3 and 12 characters. {}", appUser.getUsername());
            return false;
        }

        if (!appUser.getPassword().matches(PASSWORD)) {
            log.warn("Password must be over 5 characters. {}", appUser.getPassword());
            return false;
        }

        return true;
    }
}
