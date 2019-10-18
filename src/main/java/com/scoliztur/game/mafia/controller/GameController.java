package com.scoliztur.game.mafia.controller;


import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.RoomPlayer;
import com.scoliztur.game.mafia.entity.repositories.RoomPlayerRepositories;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.services.game.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final Game game;
    private final RoomRepositories roomRepositories;
    private final RoomPlayerRepositories playerRepositories;


    public GameController(Game game,
                          RoomRepositories roomRepositories,
                          RoomPlayerRepositories playerRepositories) {
        this.game = game;
        this.roomRepositories = roomRepositories;
        this.playerRepositories = playerRepositories;
    }


    @PostMapping("/day")
    public ResponseEntity doDay(@RequestParam("id") UUID roomId) {

        Room room = roomRepositories.getOne(roomId);
        game.day();
        room.setDay(game.isDay());
        roomRepositories.save(room);

        if (game.listForMafia != null) {
            return ResponseEntity.ok("Day has come" + "\n" + game.murderNightForMafia() + "\n"
                    + game.playerList.getPlayerList().get(0).getName() + " picks the first");
        } else {
            return ResponseEntity.ok("Day has come" + "\n" + game.playerList.getPlayerList().get(0).getName() + " picks the first");
        }
    }

    @PostMapping("/day/wake_up")
    public ResponseEntity wakeUpDay(@RequestParam("id") UUID roomId) {

        Room room = roomRepositories.getOne(roomId);

        if(!game.isCivilianInRoom()) {
            roomRepositories.delete(room);
            return ResponseEntity.ok().body("Mafia win");
        } else if (!game.isMafiaInRoom()) {
            roomRepositories.delete(room);
            return ResponseEntity.ok().body("Civilian win");
        } else if (room.isDay()) {
            game.newListForCivilian();

            List<RoomPlayer> list = game.saveRoomPlayer(room);

            for (RoomPlayer roomPlayer : list) {
                playerRepositories.save(roomPlayer);
            }

            return ResponseEntity.ok().body("Day");
        } else {
            return ResponseEntity.badRequest().body("Now night");
        }
    }

    @PostMapping("/pick")
    public ResponseEntity pick(@RequestParam("player") int numberPlayer,
                               Principal principal) {

        return ResponseEntity.ok(game.pickPlayerSelectionOrder(principal.getName() ,numberPlayer));
    }

    @GetMapping("/votes")
    public ResponseEntity<List<String>> votes() {
        return viewListCivilian();
    }


    @PostMapping("/vote")
    public ResponseEntity vote(@RequestParam("player") int numberPlayer,
                               Principal principal) {

        return ResponseEntity.ok(game.vote(principal.getName(), numberPlayer));
    }

    @PostMapping("/night")
    public ResponseEntity doNight(@RequestParam("id") UUID roomId) {

        Room room = roomRepositories.getOne(roomId);
        game.night();
        room.setDay(game.isDay());
        roomRepositories.save(room);

        if (game.listForCivilian != null) {
            return ResponseEntity.ok().body(game.murderDay() + "\n" + "Night has come");
        } else {
            return ResponseEntity.ok("Night has come");
        }
    }

    @PostMapping("/night/wake_up")
    public ResponseEntity wakeUpNight(@RequestParam("id") UUID roomId) {

        Room room = roomRepositories.getOne(roomId);

        if(!game.isCivilianInRoom()) {
            roomRepositories.delete(room);
            return ResponseEntity.ok().body("Mafia win");
        } else if (!game.isMafiaInRoom()) {
            roomRepositories.delete(room);
            return ResponseEntity.ok().body("Civilian win");
        } else if(!room.isDay()) {
            game.newListForMafia();

            List<RoomPlayer> list = game.saveRoomPlayer(room);

            for (RoomPlayer roomPlayer : list) {
                playerRepositories.save(roomPlayer);
            }

            return ResponseEntity.ok().body("Night");
        } else {
            return ResponseEntity.badRequest().body("Now day");
        }
    }

    @PostMapping("/action")
    public ResponseEntity actionPlayer(@RequestParam("player") int numberPlayer,
                                       Principal principal) {
        return ResponseEntity.ok().body(game.actionPlayer(principal.getName(), numberPlayer));
    }

    @GetMapping("/view_role")
    public ResponseEntity viewOwnRole(Principal principal) {
        return ResponseEntity.ok().body(game.findPlayerInList(principal.getName()).toString());
    }

    @GetMapping("/view/vote_selection_order/civilian")
    public ResponseEntity<List<String>> viewListCivilian() {
        return ResponseEntity.ok(game.viewOrderPlayerList(game.listForCivilian.getPlayerList()));
    }

}
