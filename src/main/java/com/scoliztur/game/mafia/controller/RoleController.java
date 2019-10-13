package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/room")
public class RoleController {

    private final RoleForRoom roleForRoom;
    private final RoomRepositories roomRepositories;
    private final UserRepositories userRepositories;

    public RoleController(RoleForRoom roleForRoom, RoomRepositories roomRepositories, UserRepositories userRepositories) {
        this.roleForRoom = roleForRoom;
        this.roomRepositories = roomRepositories;
        this.userRepositories = userRepositories;
    }

    @PostMapping("/add_role")
    public ResponseEntity addRole(@RequestParam("id") UUID roomId,
                                  @RequestParam("role") String nameRole,
                                  Principal principal) {

        Room room = roomRepositories.getOne(roomId);

        AppUser appUser = userRepositories.findUserByUsername(principal.getName());

        if (room.getCreatorId().equals(appUser.getId())) {
            if (roomRepositories.existsById(roomId)) {
                roleForRoom.addRole(nameRole);
                return ResponseEntity.ok().body("Done");
            } else {
                return ResponseEntity.badRequest().body("Such room does not exist");
            }
        } else {
            return ResponseEntity.badRequest().body("You not creator this room");
        }
    }
}
