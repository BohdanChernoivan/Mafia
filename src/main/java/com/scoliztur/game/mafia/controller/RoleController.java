package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final CompleteGame game;
    private final RoleForRoom roleForRoom;
    private final RoomRepositories roomRepositories;

    public RoleController(CompleteGame game, RoleForRoom roleForRoom, RoomRepositories roomRepositories) {
        this.game = game;
        this.roleForRoom = roleForRoom;
        this.roomRepositories = roomRepositories;
    }

    @PostMapping("/add_don")
    public String addDon(@RequestParam UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addDon(roomId);
            return "Added Don";
        } else {
            return "Such room does not exist";
        }
    }

    @PostMapping("/add_mafia")
    public String addMafia(@RequestParam UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addMafia(roomId);
            return "Added Mafia";
        } else {
            return "Such room does not exist";
        }
    }

    @PostMapping("/add_courtesan")
    public String addCourtesan(@RequestParam UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addCourtesan(roomId);
            return "Added Courtesan";
        } else {
            return "Such room does not exist";
        }
    }

    @PostMapping("/add_sheriff")
    public String addSheriff(@RequestParam UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addSheriff(roomId);
            return "Added Sheriff";
        } else {
            return "Such room does not exist";
        }
    }

    @PostMapping("/add_barman")
    public String addBarman(@RequestParam UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addBarman(roomId);
            return "Added Barman";
        } else {
            return "Such room does not exist";
        }
    }

    @PostMapping("/add_doctor")
    public String addDoctor(@RequestParam UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addDoctor(roomId);
            return "Added Doctor";
        } else {
            return "Such room does not exist";
        }
    }

    @PostMapping("/add_civilian")
    public String addCivilian(@RequestParam UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addCivilian(roomId);
            return "Added Civilian";
        } else {
            return "Such room does not exist";
        }
    }

//
//    @PostMapping("/shuffle_role")
//    public void shuffleAndFillIn(@RequestParam UUID roomId) {
//        game.playerList = roleForRoom.randomDistributionOfRole(
//                roomRepositories.getOne(roomId),
//                roleForRoom.getListOfRole()
//        );
//    }
}
