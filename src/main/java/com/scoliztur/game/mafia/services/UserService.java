package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.entity.User;
import com.scoliztur.game.mafia.entity.model.Role;
import com.scoliztur.game.mafia.entity.repositories.RoleRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.services.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserModel {

    private final UserRepositories userRepositories;
    private final RoleRepositories roleRepositories;

    @Autowired
    public UserService(UserRepositories userRepositories, RoleRepositories roleRepositories) {
        this.userRepositories = userRepositories;
        this.roleRepositories = roleRepositories;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepositories.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

//        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepositories.save(user);

        log.info("In register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {

        List<User> result = userRepositories.findAll();

        log.info("In getAll - {} users found", result.size());

        return result;
    }

    @Override
    public User findByUsername(String username) {

        User result = userRepositories.findUserByUsername(username);

        log.info("In findByUsername - user: {} found by username: {}", result, username);

        return result;
    }

    @Override
    public User findById(UUID uuid) {

        User result = userRepositories.findById(uuid).orElse(null);

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

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
