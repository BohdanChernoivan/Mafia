package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositories extends JpaRepository<AppUser, UUID> {
     AppUser findUserByUsername(String name);
     AppUser findUserByLogin(String login);
}
