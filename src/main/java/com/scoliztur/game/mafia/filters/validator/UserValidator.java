package com.scoliztur.game.mafia.filters.validator;


import com.scoliztur.game.mafia.entity.AppUser;

public class UserValidator {

    // Логин может состоять из букв, цифр, дефисов и подчёркиваний. Длина от 4 до 16 символов.
    private static final String LOGIN = "^[a-zA-Z0-9_-]{4,16}$";

    // Имя пользователя может состоять из любых латинских букв и цифр. Длина от 3 до 9 символов
    private static final String USERNAME = "^[a-zA-Z0-9]{3,9}$";

    // Пароль может состоять из любых латинских букв и цифр. Длина от 5 до 12 символов
    private static final String PASSWORD = "^[a-zA-Z0-9]{5,12}$";

    public boolean validate(AppUser appUser) {
        return appUser.getLogin().matches(LOGIN) &
                appUser.getUsername().matches(USERNAME) &&
                appUser.getPassword().matches(PASSWORD);
    }
}
