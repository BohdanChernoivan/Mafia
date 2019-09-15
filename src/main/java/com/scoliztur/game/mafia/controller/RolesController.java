package com.scoliztur.game.mafia.controller;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.User;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.*;
import com.scoliztur.game.mafia.logic.players.role.factory.RolePlayerFactory;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import com.scoliztur.game.mafia.services.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mafia/players")
public class RolesController {

    private final Game game;
    private final RoomRepositories roomRepositories;

    public RolesController(Game game, RoomRepositories roomRepositories) {
        this.game = game;
        this.roomRepositories = roomRepositories;
    }

    @GetMapping("/add/Don")
    public String addDon(@RequestBody Room room) {

        BlackPlayers blackPlayers = BlackPlayers.DON;

        roomRepositories.getOne(room.getId()).getBlackPlayers().add(blackPlayers);

        return "Added " + blackPlayers.getBlackPlayer();
    }

    @GetMapping("/add/Mafia")
    public String addMafia(@RequestBody Room room) {

        BlackPlayers blackPlayers = BlackPlayers.MAFIA;

        roomRepositories.getOne(room.getId()).getBlackPlayers().add(blackPlayers);

        return "Added " +  blackPlayers.getBlackPlayer();
    }

    @GetMapping("/add/Courtesan")
    public String addCourtesan(@RequestBody Room room) {

        BlackPlayers blackPlayers = BlackPlayers.COURTESAN;

        roomRepositories.getOne(room.getId()).getBlackPlayers().add(blackPlayers);

        return "Added " +  blackPlayers.getBlackPlayer();
    }

    @GetMapping("/add/Sheriff")
    public String addSheriff(@RequestBody Room room) {

        RedPlayers redPlayers = RedPlayers.SHERIFF;

        roomRepositories.getOne(room.getId()).getRedPlayers().add(redPlayers);

        return "Added " + redPlayers.getRedPlayer();
    }

    @GetMapping("/add/Barman")
    public String addBarman(@RequestBody Room room) {

        RedPlayers redPlayers = RedPlayers.BARMAN;

        roomRepositories.getOne(room.getId()).getRedPlayers().add(redPlayers);

        return "Added " + redPlayers.getRedPlayer();
    }

    @GetMapping("/add/Doctor")
    public String addDoctor(@RequestBody Room room) {

        RedPlayers redPlayers = RedPlayers.DOCTOR;

        roomRepositories.getOne(room.getId()).getRedPlayers().add(redPlayers);

        return "Added " + redPlayers.getRedPlayer();
    }

    @GetMapping("/add/Civilian")
    public String addCivilian(@RequestBody Room room) {

        RedPlayers redPlayers = RedPlayers.CIVILIAN;

        roomRepositories.getOne(room.getId()).getRedPlayers().add(redPlayers);

        return "Added " + redPlayers.getRedPlayer();
    }

    private void addPlayersInRoom(Player player, Room roomClone) {
//
//        if(!roomRepositories.existsById(roomClone.getId())) {
//            List<Player> players = new ArrayList<>(roomClone.getRoles());
//            int max = roomClone.getMaxSizePlayers();
//            players.add(player);
//            roomClone.setMaxSizePlayers(max + 1);
//            roomClone.setRoles(players);
//            roomRepositories.save(roomClone);
//        }
    }
}
