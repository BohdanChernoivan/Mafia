package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomRepositories roomRepositories;
    private final UserRepositories userRepositories;
    private final RoleForRoom roleForRoom;
    private final CompleteGame game;

    public RoomController(RoomRepositories roomRepositories, UserRepositories userRepositories, RoleForRoom roleForRoom, CompleteGame game) {
        this.roomRepositories = roomRepositories;
        this.userRepositories = userRepositories;
        this.roleForRoom = roleForRoom;
        this.game = game;
    }

    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestParam("name") String nameRoom,
                                     @RequestParam("max_size") int maxSize,
                                     @RequestParam("min_size") int minSize,
                                     Principal principal) {

        AppUser appUser = userRepositories.findUserByUsername(principal.getName());

        if(appUser.getRoleUser().size() == 0) {

            if (nameRoom == null) {
                return ResponseEntity.badRequest().body("Room has not name. Write name room");
            }

            Room room = new Room();
            game.nameOfList = new ArrayList<>();
            game.listOfRole = new ArrayList<>();

            if(minSize < 5) {
                room.setMinSizePlayers(5);
            } else {
                room.setMinSizePlayers(minSize);
            }

            room.setPlayersNow(1);
            room.setName(nameRoom);
            room.setMaxSizePlayers(maxSize);
            room.addUser(appUser);
            game.nameOfList.add(appUser.getUsername());
            roomRepositories.save(room);

            return ResponseEntity.ok().body("Create room. Name -> " + nameRoom);
        } else {
            return ResponseEntity.badRequest().body("You in other room");
        }
    }

    @PostMapping("/join")
    public ResponseEntity joinRoom(@RequestParam("id") UUID roomId,
                                   Principal principal) {

        AppUser appUser = userRepositories.findUserByUsername(principal.getName());

        if(!roomRepositories.existsById(roomId)) {
            return ResponseEntity.badRequest().body("Such a room does not exist");
        } else if(!userRepositories.existsById(appUser.getId())) {
            return ResponseEntity.badRequest().body("Such an appUser does not exist. AppUser does not exist");
        }

        Room room = roomRepositories.getOne(roomId);

        if(room.getMaxSizePlayers() > room.getPlayersNow()) {
            room.addUser(appUser);
            room.addPlayerNow();

            game.nameOfList.add(appUser.getUsername());
            roomRepositories.save(room);

            return ResponseEntity.ok().body(userRepositories.getOne(appUser.getId()).getUsername()
                    + " join in room -> " + roomRepositories.getOne(roomId).getName());

        } {
            return ResponseEntity.badRequest().body("Room is full");
        }
    }

    @PostMapping("/start")
    public ResponseEntity start(@RequestParam("id") UUID roomId) {

        Room room = roomRepositories.getOne(roomId);

        if(room.getPlayersNow() >= game.listOfRole.size()
                || room.getPlayersNow() >= room.getMinSizePlayers()) {
            game.newPlayerList();
            game.listOfRole = new ArrayList<>();
            return ResponseEntity.ok().body("Start game in room -> " + room.getName());
        } else {
            return ResponseEntity.badRequest().body("Roles more than players");
        }
    }
}
