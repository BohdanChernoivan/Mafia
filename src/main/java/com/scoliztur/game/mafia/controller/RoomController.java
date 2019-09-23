package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.filters.JwtAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mafia/rooms/")
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private final RoomRepositories roomRepositories;
    private final UserRepositories userRepositories;

    @Autowired
    public RoomController(RoomRepositories roomRepositories, UserRepositories userRepositories) {
        this.roomRepositories = roomRepositories;
        this.userRepositories = userRepositories;
    }

    @PostMapping("create")
    public String createRoom(@RequestBody Room room, @RequestBody AppUser appUser) {

        if(room.getName() == null) {
            log.warn("Room has not name");
            throw new RuntimeException("Write name room");
        } else if (appUser.getId() == null) {
            throw new RuntimeException("Write appUser id");
        }

        room.setPlayersNow(1);
        room.getAppUsers().add(appUser);

        roomRepositories.save(room);

        return "Create room. Name -> " + room.getName();
    }

    @PostMapping("join")
    public String joinRoom(@RequestBody Room room, @RequestBody AppUser appUser) {

        if(!roomRepositories.existsById(room.getId())) {
            throw new RuntimeException("Such a room does not exist");
        } else if (appUser.getId() == null) {
            throw new RuntimeException("Write appUser id");
        } else if(!roomRepositories.existsById(appUser.getId())) {
            log.warn("Such a appUser does not exist");
            throw new RuntimeException("AppUser does not exist");
        }

        roomRepositories.getOne(room.getId()).getAppUsers().add(appUser);

        roomRepositories.getOne(room.getId())
                .setPlayersNow(roomRepositories.getOne(room.getId()).getPlayersNow() + 1);

        return "AppUser " + userRepositories.getOne(appUser.getId()).getUsername()
                + " join in room -> " + roomRepositories.getOne(room.getId()).getName();
    }
}
