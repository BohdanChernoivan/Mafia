package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.User;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.filters.JwtAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mafia/rooms")
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private final RoomRepositories roomRepositories;
    private final UserRepositories userRepositories;

    public RoomController(RoomRepositories roomRepositories, UserRepositories userRepositories) {
        this.roomRepositories = roomRepositories;
        this.userRepositories = userRepositories;
    }

    @PostMapping("/create")
    public String createRoom(@RequestBody Room room, @RequestBody User user) {

        if(room.getName() == null) {
            log.warn("Room has not name");
            throw new RuntimeException("Write name room");
        } else if (user.getId() == null) {
            throw new RuntimeException("Write user id");
        }

        room.setPlayersNow(1);

        room.getUsers().add(user);

        roomRepositories.save(room);

        return "Create room. Name -> " + room.getName();
    }

    @PostMapping("/join")
    public String joinRoom(@RequestBody Room room, @RequestBody User user) {

        if(!roomRepositories.existsById(room.getId())) {
            throw new RuntimeException("Such a room does not exist");
        } else if (user.getId() == null) {
            throw new RuntimeException("Write user id");
        } else if(!roomRepositories.existsById(user.getId())) {
            log.warn("Such a user does not exist");
            throw new RuntimeException("User does not exist");
        }

        roomRepositories.getOne(room.getId()).getUsers().add(user);

        roomRepositories.getOne(room.getId())
                .setPlayersNow(roomRepositories.getOne(room.getId()).getPlayersNow() + 1);

        return "User " + userRepositories.getOne(user.getId()).getUsername()
                + " join in room -> " + roomRepositories.getOne(room.getId()).getName();
    }
}
