package com.scoliztur.game.mafia.validator;

import com.scoliztur.game.mafia.entity.AppUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserValidator {

    // Логин может состоять из букв, цифр, дефисов и подчёркиваний. Длина от 4 до 16 символов.
    private static final String LOGIN = "^[a-zA-Z0-9_-]{4,16}$";

    // Имя пользователя может состоять из любых латинских букв и цифр. Длина от 3 до 9 символов
    private static final String USERNAME = "^[a-zA-Z0-9]{3,12}$";

    // Пароль может состоять из любых латинских букв и цифр. Длина от 5 до 12 символов
    private static final String PASSWORD = "^[a-zA-Z0-9]{5,12}$";


    // Validator (statics)
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
