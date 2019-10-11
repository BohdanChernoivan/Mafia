package com.scoliztur.game.mafia.services.user.model;

import com.scoliztur.game.mafia.entity.AppUser;

import java.util.List;
import java.util.UUID;

public interface UserModel {

    AppUser register(AppUser appUser);

    List<AppUser> getAll();

    AppUser findByUsername(String username);

    AppUser findById(UUID uuid);

    void delete(UUID uuid);
}
