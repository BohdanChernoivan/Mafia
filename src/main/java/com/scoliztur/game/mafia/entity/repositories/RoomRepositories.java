package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepositories extends JpaRepository<Room, UUID> {
}
