package com.scoliztur.game.mafia.services.game;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.factory.RolePlayerFactory;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class RoleForRoom {

    private final RoomRepositories roomRepositories;
    private final List<Player> listOfRole;

    @Autowired
    public RoleForRoom(RoomRepositories roomRepositories, List<Player> ListOfRole) {
        this.roomRepositories = roomRepositories;
        this.listOfRole = ListOfRole;
    }


    public void addDon(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.DON;
        listOfRole.add(new RolePlayerFactory().createBlackPlayer(blackPlayers, ""));
        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }


    public void addMafia(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.MAFIA;
        listOfRole.add(new RolePlayerFactory().createBlackPlayer(blackPlayers, ""));
        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }


    public void addCourtesan(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.COURTESAN;
        listOfRole.add(new RolePlayerFactory().createBlackPlayer(blackPlayers, ""));
        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }

    public void addSheriff(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.SHERIFF;
        listOfRole.add(new RolePlayerFactory().createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addBarman(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.BARMAN;
        listOfRole.add(new RolePlayerFactory().createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addDoctor(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.DOCTOR;
        listOfRole.add(new RolePlayerFactory().createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addCivilian(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.CIVILIAN;
        listOfRole.add(new RolePlayerFactory().createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public PlayerList randomDistributionOfRole(Room room, List<String> usernameList, List<Player> listOfRole) {

        PlayerList cloneList = new PlayerList();

        int minPlayersInRoom = room.getPlayersNow();

        if(minPlayersInRoom > listOfRole.size()) {
            int notEnoughPlayers = minPlayersInRoom - listOfRole.size();
            for (int i = 0; i < notEnoughPlayers; i++) {
                listOfRole.add(new RolePlayerFactory().createRedPlayer(RedPlayers.CIVILIAN, ""));
            }
        }

        Collections.shuffle(listOfRole);

        for (Player player : listOfRole) {
            insertRndUsername(player, usernameList, cloneList);
        }

        return cloneList;
    }

    private void insertRndUsername(Player player, List<String> usernameList, PlayerList playerList) {

        int rndElementFromListNames = new Random().nextInt(usernameList.size());
        player.setName(usernameList.get(rndElementFromListNames));
        playerList.insertPlayer(player);
        usernameList.remove(usernameList.get(rndElementFromListNames));
    }

//    private PlayerList randomDistributionOfRole(Room room, List<String> users, List<Player> listOfRole) {
//
//        PlayerList cloneList = new PlayerList();
//
//        int needPlayersInRoom = room.getMinSizePlayers();
//
//        if(needPlayersInRoom < listOfRole.size()) {
//            int notEnoughPlayers = needPlayersInRoom - listOfRole.size();
//            for (int i = 0; i < notEnoughPlayers; i++) {
//                listOfRole.add(new RolePlayerFactory().createRedPlayer(RedPlayers.CIVILIAN, ""));
//            }
//        }
//
//        for (Player player : listOfRole) {
//            for (String name : users) {
//                player.setName(name);
//                cloneList.insertPlayer(player);
//            }
//        }
//
//        return cloneList;
//    }
}
