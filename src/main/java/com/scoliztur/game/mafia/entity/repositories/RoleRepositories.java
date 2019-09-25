package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepositories extends JpaRepository<RoleUser, UUID> {
    RoleUser findByName(String name);
}