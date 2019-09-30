package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import com.scoliztur.game.mafia.services.factory.PlayerRoleBindingService;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {

    private final CompleteGame game;
    private final PlayerRoleBindingService playerFactory;
    private final RoleForRoom roleForRoom;

    public GameController(CompleteGame game, PlayerRoleBindingService playerFactory, RoleForRoom roleForRoom) {
        this.game = game;
        this.playerFactory = playerFactory;
        this.roleForRoom = roleForRoom;
    }


    @GetMapping("/add_role")
    public void add() {

        game.newPlayerList();

        game.listOfRole = new ArrayList<>();

        BlackPlayers a1 = BlackPlayers.DON;
        game.listOfRole.add(playerFactory.createBlackPlayer(a1, ""));

        BlackPlayers a2 = BlackPlayers.COURTESAN;
        game.listOfRole.add(playerFactory.createBlackPlayer(a2, ""));

        BlackPlayers a3 = BlackPlayers.MAFIA;
        game.listOfRole.add(playerFactory.createBlackPlayer(a3, ""));

        RedPlayers a4 = RedPlayers.CIVILIAN;
        game.listOfRole.add(playerFactory.createRedPlayer(a4, ""));

        RedPlayers a5 = RedPlayers.DOCTOR;
        game.listOfRole.add(playerFactory.createRedPlayer(a5, ""));

        RedPlayers a6 = RedPlayers.BARMAN;
        game.listOfRole.add(playerFactory.createRedPlayer(a6, ""));

        RedPlayers a7 = RedPlayers.SHERIFF;
        game.listOfRole.add(playerFactory.createRedPlayer(a7, ""));
    }

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



    @GetMapping("/a1")
    public String[] a1() {

        String[] mapView = new String[game.playerList.getPlayerList().size()];

        for (int i = 0; i < mapView.length; i++) {
            mapView[i] = game.playerList.getPlayerList().get(i).toString() + " = " + game.playerList.getPlayerList().get(i).getName();
        }
        String[] map = mapView;

        return map;
//        return game.playerList.getPlayerList().get(1).getName() + " = " + game.playerList.getPlayerList().get(1).toString();
    }

    @GetMapping("/a2")
    public String a2() {
        return game.playerList.getPlayerList().get(2).getName() + " = " + game.playerList.getPlayerList().get(2).toString();
    }

    @GetMapping("/a3")
    public String a3() {
        return game.playerList.getPlayerList().get(3).getName() + " = " + game.playerList.getPlayerList().get(3).toString();
    }

    @GetMapping("/a4")
    public String a4() {
        return game.playerList.getPlayerList().get(4).getName() + " = " + game.playerList.getPlayerList().get(4).toString();
    }

    @GetMapping("/a5")
    public String a5() {
        return game.playerList.getPlayerList().get(5).getName() + " = " + game.playerList.getPlayerList().get(5).toString();
    }

    @GetMapping("/a6")
    public String a6() {
        return game.playerList.getPlayerList().get(6).getName() + " = " + game.playerList.getPlayerList().get(6).toString();
    }

    @GetMapping("/a7")
    public String a7() {
        return game.playerList.getPlayerList().get(7).getName() + " = " + game.playerList.getPlayerList().get(7).toString();
    }

    @GetMapping("/a8")
    public String a8() {
        return game.playerList.getPlayerList().get(8).getName() + " = " + game.playerList.getPlayerList().get(8).toString();
    }

    @GetMapping("/a9")
    public String a9() {
        return game.playerList.getPlayerList().get(9).getName() + " = " + game.playerList.getPlayerList().get(9).toString();
    }
}
