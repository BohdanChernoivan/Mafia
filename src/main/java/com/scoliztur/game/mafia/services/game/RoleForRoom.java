package com.scoliztur.game.mafia.services.game;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.services.factory.PlayerRoleBindingService;
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
    private final PlayerRoleBindingService playerFactory;
    private final List<Player> listOfRole;

    public List<Player> getListOfRole() {
        return listOfRole;
    }

    @Autowired
    public RoleForRoom(RoomRepositories roomRepositories, PlayerRoleBindingService playerFactory, List<Player> ListOfRole) {
        this.roomRepositories = roomRepositories;
        this.playerFactory = playerFactory;
        this.listOfRole = ListOfRole;
    }


    public void addDon(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.DON;
        listOfRole.add(playerFactory.createBlackPlayer(blackPlayers, ""));
        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }


    public void addMafia(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.MAFIA;
        listOfRole.add(playerFactory.createBlackPlayer(blackPlayers, ""));
        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }


    public void addCourtesan(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.COURTESAN;
        listOfRole.add(playerFactory.createBlackPlayer(blackPlayers, ""));
        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }

    public void addSheriff(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.SHERIFF;
        listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addBarman(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.BARMAN;
        listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addDoctor(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.DOCTOR;
        listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addCivilian(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.CIVILIAN;
        listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public static PlayerList randomDistributionOfRole(Room room, List<Player> listOfRole) {

        PlayerList cloneList = new PlayerList();

        int minPlayersInRoom = room.getPlayersNow();

        if(minPlayersInRoom > listOfRole.size()) {
            int notEnoughPlayers = minPlayersInRoom - listOfRole.size();
            for (int i = 0; i < notEnoughPlayers; i++) {
                listOfRole.add(new PlayerRoleBindingService().createRedPlayer(RedPlayers.CIVILIAN, ""));
            }
        }

        Collections.shuffle(listOfRole);

        for (Player player : listOfRole) {
            insertRndUsername(player, room.getAppUsers(), cloneList);
        }

        return cloneList;
    }

    private static void insertRndUsername(Player player, List<AppUser> usernameList, PlayerList playerList) {

        int rndElementFromListNames = new Random().nextInt(usernameList.size());
        player.setName(usernameList.get(rndElementFromListNames).getUsername());
        playerList.insertPlayer(player);
        usernameList.remove(usernameList.get(rndElementFromListNames));
    }
}
