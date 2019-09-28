package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import com.scoliztur.game.mafia.services.factory.PlayerRoleBindingService;
import com.scoliztur.game.mafia.services.game.CompleteGame;
import com.scoliztur.game.mafia.services.game.RoleForRoom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final CompleteGame game;
    private final PlayerRoleBindingService playerFactory;
    private List<Player> listOfRole;
    private final RoleForRoom roleForRoom;

    public GameController(CompleteGame game, PlayerRoleBindingService playerFactory, RoleForRoom roleForRoom) {
        this.game = game;
        this.playerFactory = playerFactory;
        this.roleForRoom = roleForRoom;
    }

    @GetMapping("/start")
    public void start() {
        listOfRole = new ArrayList<>();
        game.newPlayerList();
    }

    @GetMapping("/add_role")
    public void add() {

        BlackPlayers a1 = BlackPlayers.DON;
        listOfRole.add(playerFactory.createBlackPlayer(a1, ""));

        BlackPlayers a2 = BlackPlayers.COURTESAN;
        listOfRole.add(playerFactory.createBlackPlayer(a2, ""));

        BlackPlayers a3 = BlackPlayers.MAFIA;
        listOfRole.add(playerFactory.createBlackPlayer(a3, ""));

        RedPlayers a4 = RedPlayers.CIVILIAN;
        listOfRole.add(playerFactory.createRedPlayer(a4, ""));

        RedPlayers a5 = RedPlayers.DOCTOR;
        listOfRole.add(playerFactory.createRedPlayer(a5, ""));

        RedPlayers a6 = RedPlayers.BARMAN;
        listOfRole.add(playerFactory.createRedPlayer(a6, ""));

        RedPlayers a7 = RedPlayers.SHERIFF;
        listOfRole.add(playerFactory.createRedPlayer(a7, ""));
    }

    @GetMapping("/shuffle_role")
    public void shuffleAndFillIn() {

        List<String> stringList = new ArrayList<>();

        stringList.add("a1");
        stringList.add("a2");
        stringList.add("a3");
        stringList.add("a4");
        stringList.add("a5");
        stringList.add("a6");
        stringList.add("a7");
        stringList.add("a8");
        stringList.add("a9");


        game.playerList = roleForRoom.randomDistributionOfRole(stringList);
    }

    @GetMapping("/a1")
    public String a1() {
        return game.playerList.getPlayerList().get(1).getName() + " = " + game.playerList.getPlayerList().get(1).toString();
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
