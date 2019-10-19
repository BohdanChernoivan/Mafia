package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositories extends JpaRepository<AppUser, UUID> {
     AppUser findUserByLogin(String login);
     AppUser findUserByUsername(String username);
}
