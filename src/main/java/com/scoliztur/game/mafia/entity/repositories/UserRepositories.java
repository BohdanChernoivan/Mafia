package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositories extends JpaRepository<User, UUID> {

     User findUserByUsername(String name);
}
