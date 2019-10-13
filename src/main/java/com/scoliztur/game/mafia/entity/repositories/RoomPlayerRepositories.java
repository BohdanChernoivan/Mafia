package com.scoliztur.game.mafia.entity.repositories;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.RoomPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomPlayerRepositories extends JpaRepository<RoomPlayer, UUID> {
    List<RoomPlayer> findAllByRoomUser(Room room);

    RoomPlayer findByNickname(String nickname);

    boolean existsRoomPlayerByNickname(String nickname);
}
