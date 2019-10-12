package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/room")
public class RoleController {

    private final RoleForRoom roleForRoom;
    private final RoomRepositories roomRepositories;

    public RoleController(RoleForRoom roleForRoom, RoomRepositories roomRepositories) {
        this.roleForRoom = roleForRoom;
        this.roomRepositories = roomRepositories;
    }

    @PostMapping("/add_role")
    public ResponseEntity addRole(@RequestParam("id") UUID roomId,
                                  @RequestParam("role") String nameRole) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addRole(nameRole);
            return ResponseEntity.ok().body("Done");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }
}
