package com.scoliztur.game.mafia.services.model;

import com.scoliztur.game.mafia.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserModel {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(UUID uuid);

    void delete(UUID uuid);
}
