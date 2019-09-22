package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mafia/players")
public class RolesController {

    private final CompleteGame game;
    private final RoleForRoom roleForRoom;
    private final RoomRepositories roomRepositories;

    @Autowired
    public RolesController(CompleteGame game, RoleForRoom roleForRoom, RoomRepositories roomRepositories) {
        this.game = game;
        this.roleForRoom = roleForRoom;
        this.roomRepositories = roomRepositories;
    }

    @GetMapping("/add/Don")
    public String addDon(@RequestParam UUID roomId) {
        if(roomRepositories.existsById(roomId)) {
        roleForRoom.addDon(roomId);
            return "";
        } else {
            return "";
        }
    }

    @GetMapping("/add/Mafia")
    public String addMafia(@RequestParam UUID roomId) {
        if(roomRepositories.existsById(roomId)) {
        roleForRoom.addMafia(roomId);
            return "";
        } else {
            return "";
        }
    }

    @GetMapping("/add/Courtesan")
    public String addCourtesan(@RequestParam UUID roomId) {
        if(roomRepositories.existsById(roomId)) {
        roleForRoom.addCourtesan(roomId);
            return "";
        } else {
            return "";
        }
    }

    @GetMapping("/add/Sheriff")
    public String addSheriff(@RequestParam UUID roomId) {
        if(roomRepositories.existsById(roomId)) {
        roleForRoom.addSheriff(roomId);
            return "";
        } else {
            return "";
        }
    }

    @GetMapping("/add/Barman")
    public String addBarman(@RequestParam UUID roomId) {
        if(roomRepositories.existsById(roomId)) {
        roleForRoom.addBarman(roomId);
            return "";
        } else {
            return "";
        }
    }

    @GetMapping("/add/Doctor")
    public String addDoctor(@RequestParam UUID roomId) {
        if(roomRepositories.existsById(roomId)) {
        roleForRoom.addDoctor(roomId);
            return "";
        } else {
            return "";
        }
    }

    @GetMapping("/add/Civilian")
    public String addCivilian(@RequestParam UUID roomId) {
        if(roomRepositories.existsById(roomId)) {
            roleForRoom.addCivilian(roomId);
            return "";
        } else {
            return "";
        }
    }


}
