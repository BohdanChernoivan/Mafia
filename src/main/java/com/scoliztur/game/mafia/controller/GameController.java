package com.scoliztur.game.mafia.controller;


import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {

    private final CompleteGame game;
    private final RoleForRoom roleForRoom;
    private int countPlayer;

    public GameController(CompleteGame game, RoleForRoom roleForRoom) {
        this.game = game;
        this.roleForRoom = roleForRoom;
    }

    @GetMapping("/add_role")
    public void add() {
        game.newPlayerList();
        game.newListForCivilian();
        game.listOfRole = new ArrayList<>();
    }

    //TODO countPlayer = -1
    @GetMapping("/day")
    public String day() {
        game.day();
        countPlayer = 0;
        return "day";
    }

    @PostMapping("/pick")
    public void start(@RequestParam("player") int numberPlayer) {

            if (game.playerList.getPlayerList().get(numberPlayer) != null) {
                countPlayer++;
                if (game.playerList.getPlayerList().get(countPlayer) != null) {
                    game.playerList.getPlayerList().get(countPlayer)
                            .pick(game.listForCivilian, game.playerList.getPlayerList().get(numberPlayer), game.isDay());

                    log.info("{} picks {}", game.playerList.getPlayerList().get(countPlayer).getName(),
                            game.playerList.getPlayerList().get(numberPlayer).getName());
                } else {
                    throw new RuntimeException("Such player none");
                }
            } else {
                throw new RuntimeException("No choice");
//            else
//            log.info("Player does not pick anyone");
        }
    }

    @GetMapping("/votes")
    public String[] votes() {
        countPlayer = 0;
        return viewListCivilian();
    }

    //TODO
    @PostMapping("/vote")
    public void vote(@RequestParam("player") int numberPlayer) {

            if (game.listForCivilian.getPlayerList().get(numberPlayer) != null) {
                countPlayer++;
                if (game.playerList.getPlayerList().get(countPlayer) != null) {
                    game.playerList.getPlayerList().get(countPlayer)
                            .vote(game.listForCivilian, game.listForCivilian.getPlayerList().get(numberPlayer), game.isDay());

                    log.info("{} voted for {}", game.listForCivilian.getPlayerList().get(countPlayer).getName(),
                            game.listForCivilian.getPlayerList().get(numberPlayer).getName());
                } else {
                    throw new RuntimeException("Such player none");
                }
            } else {
                throw new RuntimeException("No choice");
            /*countPlayer++;
            log.info("Player does not vote");*/
        }
    }

    @GetMapping("/night")
    public String night() {
        game.night();
        countPlayer = 0;
        return "night";
    }

//    public void barman() {
//
//    }
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
    public String[] viewPlayers() {
        return game.viewLinePlayer();
    }

    @GetMapping("/view/vote/mafia")
    public String[] viewListMafia() {
        return game.viewListMafia();
    }

    @GetMapping("/view/vote/civilian")
    public String[] viewListCivilian() {
        return game.viewListCivilian();
    }
}
