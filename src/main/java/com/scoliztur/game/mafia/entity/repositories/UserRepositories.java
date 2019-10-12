package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositories extends JpaRepository<AppUser, UUID> {
     AppUser findUserByLogin(String login);
     AppUser findUserByUsername(String username);
     AppUser findUserByRoomUser(Room room);
}
