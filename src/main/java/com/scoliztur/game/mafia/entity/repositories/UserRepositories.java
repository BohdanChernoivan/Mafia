package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositories extends JpaRepository<User, UUID> {
     User findUserByUsername(String name);
}
