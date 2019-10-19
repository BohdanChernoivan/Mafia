package com.scoliztur.game.mafia.services.user;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.RoleUser;
import com.scoliztur.game.mafia.entity.repositories.RoleRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.security.roles.RoleStatus;
import com.scoliztur.game.mafia.services.user.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService implements UserModel {

    private final UserRepositories userRepositories;
    private final RoleRepositories roleRepositories;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepositories userRepositories, RoleRepositories roleRepositories, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.roleRepositories = roleRepositories;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AppUser register(AppUser appUser) {
        RoleUser roleUser = roleRepositories.findByName(RoleStatus.USER.name());
        Set<RoleUser> userRoleUsers = new HashSet<>();
        userRoleUsers.add(roleUser);

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRoleUser(userRoleUsers);

        userRepositories.save(appUser);

        log.info("In register - appUser: {} successfully registered", appUser);

        return appUser;
    }

    @Override
    public List<AppUser> getAll() {

        List<AppUser> result = userRepositories.findAll();

        log.info("In getAll - {} appUsers found", result.size());

        return result;
    }

    @Override
    public AppUser findByUsername(String username) {

        AppUser result = userRepositories.findUserByUsername(username);

        log.info("In findUserByLogin - user: {} found by username: {}", result, username);

        return result;
    }

    @Override
    public AppUser findById(UUID uuid) {

        AppUser result = userRepositories.findById(uuid).orElse(null);

        if(result == null) {
            log.warn("In findById - no user found by id: {}", uuid);
        }
        log.info("In findById - user: {} found by username: {}", result, uuid);

        return result;
    }

    @Override
    public void delete(UUID uuid) {

        userRepositories.deleteById(uuid);

        log.info("In findById - user with id: {} successfully deleted", uuid);
    }
}
