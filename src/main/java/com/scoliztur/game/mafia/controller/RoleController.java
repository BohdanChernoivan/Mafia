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
@RequestMapping("/room/role")
public class RoleController {

    private final RoleForRoom roleForRoom;
    private final RoomRepositories roomRepositories;

    public RoleController(RoleForRoom roleForRoom, RoomRepositories roomRepositories) {
        this.roleForRoom = roleForRoom;
        this.roomRepositories = roomRepositories;
    }

    @PostMapping("/add_don")
    public ResponseEntity addDon(@RequestParam("id") UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addDon(roomId);
            return ResponseEntity.ok().body("Don");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }

    @PostMapping("/add_mafia")
    public ResponseEntity addMafia(@RequestParam("id") UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addMafia(roomId);
            return ResponseEntity.ok().body("Mafia");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }

    @PostMapping("/add_courtesan")
    public ResponseEntity addCourtesan(@RequestParam("id") UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addCourtesan(roomId);
            return ResponseEntity.ok().body("Courtesan");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }

    @PostMapping("/add_sheriff")
    public ResponseEntity addSheriff(@RequestParam("id") UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addSheriff(roomId);
            return ResponseEntity.ok().body("Sheriff");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }

    @PostMapping("/add_barman")
    public ResponseEntity addBarman(@RequestParam("id") UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addBarman(roomId);
            return ResponseEntity.ok().body("Barman");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }

    @PostMapping("/add_doctor")
    public ResponseEntity addDoctor(@RequestParam("id") UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addDoctor(roomId);
            return ResponseEntity.ok().body("Doctor");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }

    @PostMapping("/add_civilian")
    public ResponseEntity addCivilian(@RequestParam("id") UUID roomId) {
        if (roomRepositories.existsById(roomId)) {
            roleForRoom.addCivilian(roomId);
            return ResponseEntity.ok().body("Civilian");
        } else {
            return ResponseEntity.badRequest().body("Such room does not exist");
        }
    }
}
