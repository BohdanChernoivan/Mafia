package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepositories extends JpaRepository<Role, UUID> {

    Role findByName(String name);
}
