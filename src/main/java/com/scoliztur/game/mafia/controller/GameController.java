package com.scoliztur.game.mafia.controller;


import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.RoomPlayer;
import com.scoliztur.game.mafia.entity.repositories.RoomPlayerRepositories;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final CompleteGame game;
    private final RoleForRoom roleForRoom;
    private final RoomRepositories roomRepositories;
    private final RoomPlayerRepositories playerRepositories;


    public GameController(CompleteGame game, RoleForRoom roleForRoom,
                          RoomRepositories roomRepositories,
                          RoomPlayerRepositories playerRepositories) {
        this.game = game;
        this.roleForRoom = roleForRoom;
        this.roomRepositories = roomRepositories;
        this.playerRepositories = playerRepositories;
    }

    @PostMapping("/shuffle")
    public ResponseEntity shuffleRole(@RequestParam("id") UUID roomId) {

        game.playerList = roleForRoom.randomDistributionOfRole(roomId);

        return ResponseEntity.ok().body("Done");
    }


    @PostMapping("/day")
    public ResponseEntity day(@RequestParam("id") UUID roomId) {

        game.day();
        game.newListForCivilian();
        game.countPlayer = -1;

        Room room = roomRepositories.getOne(roomId);

        room.setDay(game.isDay());

        roomRepositories.save(room);

        List<RoomPlayer> list = game.saveRoomPlayer(room);

        for (RoomPlayer roomPlayer : list) {
            playerRepositories.save(roomPlayer);
        }

        if(game.listForMafia != null) {
            return ResponseEntity.ok("day" + "\n" + game.murderNightForMafia());
        }
        return ResponseEntity.ok("day + \n" + game.playerList.getPlayerList().get(0).getName() + " picks the first");
    }


    @PostMapping("/pick")
    public ResponseEntity pick(@RequestParam("player") int numberPlayer) {

        return ResponseEntity.ok(game.pickPlayerSelectionOrder(numberPlayer));
    }

    @GetMapping("/votes")
    public List<String> votes() {
        return viewListCivilian();
    }


    @PostMapping("/vote")
    public ResponseEntity vote(@RequestParam("this_player") int thisNumberPlayer,
                               @RequestParam("player") int numberPlayer) {
         return ResponseEntity.ok(game.vote(thisNumberPlayer, numberPlayer));
    }

    @PostMapping("/night")
    public ResponseEntity night(@RequestParam("id") UUID roomId) {

        game.newListForMafia();
        game.night();

        Room room = roomRepositories.getOne(roomId);

        room.setDay(game.isDay());

        roomRepositories.save(room);

        List<RoomPlayer> list = game.saveRoomPlayer(room);

        for (RoomPlayer roomPlayer : list) {
            playerRepositories.save(roomPlayer);
        }

        if(game.listForCivilian != null) {
            return ResponseEntity.ok("night" + "\n" +  game.murderDay());
        }
        return ResponseEntity.ok("night");
    }

    @PostMapping("/findSheriff")
    public ResponseEntity actionPlayer(@RequestParam("this_player") int thisNumberPlayer,
                                       @RequestParam("player") int numberPlayer) {
        return ResponseEntity.ok().body(game.actionPlayerNight(thisNumberPlayer, numberPlayer));
    }


    @GetMapping("/view/players")
    public List<String> viewPlayers() {
        return game.viewOrderPlayerList(game.playerList.getPlayerList());
    }

    @GetMapping("/view/vote_selection_order/mafia")
    public List<String> viewListMafia() {
        return game.viewOrderPlayerList(game.listForMafia.getPlayerList());
    }

    @GetMapping("/view/vote_selection_order/civilian")
    public List<String> viewListCivilian() {
        return game.viewOrderPlayerList(game.listForCivilian.getPlayerList());
    }

}
