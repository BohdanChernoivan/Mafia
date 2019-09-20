package com.scoliztur.game.mafia.filters.validator;


import com.scoliztur.game.mafia.entity.User;

public class Validator {

    private static final String EMAIL_VALIDATION_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";

    public boolean userValidate(User user) {
        var userClone = user != null;
        if (userClone) {
            userClone = (user.getUsername() != null) && (user.getPassword() != null);
            if (userClone) {
                userClone = isNicknameValid(user.getUsername())
                        && isPasswordValid(user.getPassword());
            }
        }
        return userClone;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

    private boolean isEmailValid(String email) {
        return email.toLowerCase().matches(EMAIL_VALIDATION_REGEX);
    }

    private boolean isNicknameValid(String nickName) {
        return nickName.length() > 3;
    }
}
