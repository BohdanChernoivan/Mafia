package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.RoomPlayer;
import com.scoliztur.game.mafia.entity.repositories.RoomPlayerRepositories;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.services.factory.PlayerRoleBindingService;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomRepositories roomRepositories;
    private final UserRepositories userRepositories;
    private final RoomPlayerRepositories playerRepositories;
    private final CompleteGame game;

    public RoomController(RoomRepositories roomRepositories, UserRepositories userRepositories, RoomPlayerRepositories playerRepositories, CompleteGame game) {
        this.roomRepositories = roomRepositories;
        this.userRepositories = userRepositories;
        this.playerRepositories = playerRepositories;
        this.game = game;
    }

    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestParam("name") String nameRoom,
                                     @RequestParam("max") int maxSize,
                                     @RequestParam("min") int minSize,
                                     Principal principal) {

        AppUser appUser = userRepositories.findUserByUsername(principal.getName());

        if(appUser.getRoomUser() == null) {

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
            room.setDay(true);
            room.setCreatorId(appUser.getId());

            appUser.setRoomUser(room);

            game.nameOfList.add(appUser.getUsername());
            roomRepositories.save(room);
            userRepositories.save(appUser);

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
            appUser.setRoomUser(room);
            room.setPlayersNow(room.getPlayersNow() + 1);

            game.nameOfList.add(appUser.getUsername());
            userRepositories.save(appUser);
            roomRepositories.save(room);

            return ResponseEntity.ok().body(userRepositories.getOne(appUser.getId()).getUsername()
                    + " join in room -> " + roomRepositories.getOne(roomId).getName());

        } {
            return ResponseEntity.badRequest().body("Room is full");
        }
    }

    @PostMapping("/restore")
    public ResponseEntity restore(@RequestParam("id") UUID roomId) {

        Room room = roomRepositories.getOne(roomId);

        game.newPlayerList();

        List<RoomPlayer> roomPlayerList = playerRepositories.findAllByRoomUser(room);

        return ResponseEntity.ok().body(game.restore(roomPlayerList, room.isDay()));

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
