package com.scoliztur.game.mafia.validator;


import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    // Логин может состоять из букв, цифр, дефисов и подчёркиваний. Длина от 4 до 16 символов.
    private static final String LOGIN = "^[a-zA-Z0-9_-]{4,16}$";

    // Имя пользователя может состоять из любых латинских букв и цифр. Длина от 3 до 9 символов
    private static final String USERNAME = "^[a-zA-Z0-9]{3,12}$";

    // Пароль может состоять из любых латинских букв и цифр. Длина от 5 до 12 символов
    private static final String PASSWORD = "^[a-zA-Z0-9]{5,12}$";


    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {
        return AppUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AppUser appUser = (AppUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if(!appUser.getLogin().matches(LOGIN)) {
            errors.rejectValue("login", "Size.userForm.login");
        }

        if(appUser.getUsername().length() < 3 || appUser.getUsername().length() > 12) {
            if(!appUser.getUsername().matches(USERNAME)) {
                errors.rejectValue("username", "Size.userForm.username");
            }
        }

        if (userService.findByUsername(appUser.getUsername())!= null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if(appUser.getPassword().length() < 5 || appUser.getPassword().length() > 12) {
            if(!appUser.getPassword().matches(PASSWORD)) {
                errors.rejectValue("password", "Size.userForm.password");
            }
        }

        if(!appUser.getConfirmPassword().equals(appUser.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
