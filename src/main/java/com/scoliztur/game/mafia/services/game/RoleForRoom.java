package com.scoliztur.game.mafia.services.game;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.repositories.RoomRepositories;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.services.factory.PlayerRoleBindingService;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleForRoom {

    private final RoomRepositories roomRepositories;
    private final PlayerRoleBindingService playerFactory;
    private final CompleteGame completeGame;


    public RoleForRoom(RoomRepositories roomRepositories, PlayerRoleBindingService playerFactory, CompleteGame completeGame) {
        this.roomRepositories = roomRepositories;
        this.playerFactory = playerFactory;
        this.completeGame = completeGame;
    }


    public void addDon(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.DON;
        completeGame.listOfRole.add(playerFactory.createBlackPlayer(blackPlayers, ""));
//        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }


    public void addMafia(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.MAFIA;
        completeGame.listOfRole.add(playerFactory.createBlackPlayer(blackPlayers, ""));
//        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }


    public void addCourtesan(UUID roomId) {
        BlackPlayers blackPlayers = BlackPlayers.COURTESAN;
        completeGame.listOfRole.add(playerFactory.createBlackPlayer(blackPlayers, ""));
//        roomRepositories.getOne(roomId).getBlackPlayers().add(blackPlayers);
    }

    public void addSheriff(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.SHERIFF;
        completeGame.listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
//        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addBarman(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.BARMAN;
        completeGame.listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
//        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addDoctor(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.DOCTOR;
        completeGame.listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
//        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }


    public void addCivilian(UUID roomId) {
        RedPlayers redPlayers = RedPlayers.CIVILIAN;
        completeGame.listOfRole.add(playerFactory.createRedPlayer(redPlayers, ""));
//        roomRepositories.getOne(roomId).getRedPlayers().add(redPlayers);
    }

    public PlayerList randomDistributionOfRole(UUID id) {

        PlayerList cloneList = new PlayerList();

        /*Room room = roomRepositories.getOne(id);*/

        int minPlayersInRoom = 5/*room.getMinSizePlayers()*/;

        if(minPlayersInRoom <= completeGame.nameOfList.size()) {
            if(completeGame.nameOfList.size() > completeGame.listOfRole.size()) {
                int notEnoughPlayers = completeGame.nameOfList.size() - completeGame.listOfRole.size();
                for (int i = 0; i < notEnoughPlayers; i++) {
                    completeGame.listOfRole.add(playerFactory.createRedPlayer(RedPlayers.CIVILIAN, ""));
                }
            }
        } else {
            throw new RuntimeException("Not enough players");
        }

        Collections.shuffle(completeGame.listOfRole);

        for (Player player : completeGame.listOfRole) {
            insertRndUsername(player, completeGame.nameOfList, cloneList);
        }

        return cloneList;
    }

    private void insertRndUsername(Player player, List<String> usernameList, PlayerList playerList) {

        int rndElementFromListNames = new Random().nextInt(usernameList.size());
        player.setName(usernameList.get(rndElementFromListNames));
        playerList.insertPlayer(player);
        usernameList.remove(usernameList.get(rndElementFromListNames));
    }

}
