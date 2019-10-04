package com.scoliztur.game.mafia.controller;


import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {

    private final CompleteGame game;
    private final RoleForRoom roleForRoom;

    public GameController(CompleteGame game, RoleForRoom roleForRoom) {
        this.game = game;
        this.roleForRoom = roleForRoom;
    }

    @GetMapping("/shuffle_role")
    public void add(@RequestParam("id") UUID roomId) {
        game.playerList = roleForRoom.randomDistributionOfRole(roomId);
    }

    @GetMapping("/day")
    public ResponseEntity day() {
        game.day();
        game.newListForCivilian();
        game.countPlayer = -1;
        return ResponseEntity.ok("day");
    }

    @PostMapping("/pick")
    public ResponseEntity pick(@RequestParam("player") int numberPlayer) {
        return ResponseEntity.ok(game.pickSelectionOrder(numberPlayer));
    }

    @GetMapping("/votes")
    public List<String> votes() {
        game.countPlayer = -1;
        return viewListCivilian();
    }


    @PostMapping("/vote")
    public ResponseEntity vote(@RequestParam("player") int numberPlayer) {
         return ResponseEntity.ok(game.voteSelectionOrder(numberPlayer));
    }

    @GetMapping("/night")
    public ResponseEntity night() {
        game.night();
        game.newListForMaffiozi();
        game.countPlayer = -1;
        return ResponseEntity.ok("night");
    }

    @PostMapping
    public void barman(@RequestParam("player") int numberPlayer) {



    }
//
//    public void courtesan() {
//
//    }
//
//    public void mafia() {
//
//    }
//
//    public void sheriff() {
//
//    }
//
//    public void don() {
//
//    }
//
//    public void doctor() {
//
//    }



    @PostMapping("/shuffle_role")
    public void shuffleAndFillIn(@RequestParam UUID id) {


        game.nameOfList = new ArrayList<>();

        game.nameOfList.add("a1");
        game.nameOfList.add("a2");
        game.nameOfList.add("a3");
        game.nameOfList.add("a4");
        game.nameOfList.add("a5");
        game.nameOfList.add("a6");
        game.nameOfList.add("a7");
        game.nameOfList.add("a8");
        game.nameOfList.add("a9");

        game.playerList = roleForRoom.randomDistributionOfRole(id);
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
