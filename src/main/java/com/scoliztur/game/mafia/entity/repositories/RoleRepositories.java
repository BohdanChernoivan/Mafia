package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepositories extends JpaRepository<RoleUser, UUID> {
    RoleUser findByName(String name);
}
