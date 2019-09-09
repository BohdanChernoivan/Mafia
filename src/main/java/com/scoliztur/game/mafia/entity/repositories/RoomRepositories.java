package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepositories extends JpaRepository<Room, UUID> {
}
