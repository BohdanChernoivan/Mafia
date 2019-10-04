package com.scoliztur.game.mafia.controller;


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

    public GameController(CompleteGame game, RoleForRoom roleForRoom) {
        this.game = game;
        this.roleForRoom = roleForRoom;
    }

    @PostMapping("/shuffle_role")
    public void add(@RequestParam("id") UUID roomId) {
        game.playerList = roleForRoom.randomDistributionOfRole(roomId);
    }

    @GetMapping("/day")
    public ResponseEntity day() {
        game.day();
        game.newListForCivilian();
        game.countPlayer = -1;
        if(game.listForMafia != null) {
            return ResponseEntity.ok("day" + "\n" + game.murderNightForMafia());

        }
        return ResponseEntity.ok("day");
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

    @GetMapping("/night")
    public ResponseEntity night() {
        game.newListForMafia();
        game.night();
        if(game.listForCivilian != null) {
            return ResponseEntity.ok("night" + "\n" +  game.murderDay());
        }
        return ResponseEntity.ok("night");
    }

    @PostMapping("/action")
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
