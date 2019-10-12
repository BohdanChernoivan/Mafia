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

    public void addRole(String nameRole) {

        switch (nameRole) {
            case "Don":
                completeGame.listOfRole.add(playerFactory.createPlayer(BlackPlayers.DON.getNameRole(), ""));
                break;
            case "Mafia":
                completeGame.listOfRole.add(playerFactory.createPlayer(BlackPlayers.MAFIA.getNameRole(), ""));
                break;
            case "Courtesan":
                completeGame.listOfRole.add(playerFactory.createPlayer(BlackPlayers.COURTESAN.getNameRole(), ""));
                break;
            case "Sheriff":
                completeGame.listOfRole.add(playerFactory.createPlayer(RedPlayers.SHERIFF.getNameRole(), ""));
                break;
            case "Barman":
                completeGame.listOfRole.add(playerFactory.createPlayer(RedPlayers.BARMAN.getNameRole(), ""));
                break;
            case "Doctor":
                completeGame.listOfRole.add(playerFactory.createPlayer(RedPlayers.DOCTOR.getNameRole(), ""));
                break;
            case "Civilian" :
                completeGame.listOfRole.add(playerFactory.createPlayer(RedPlayers.CIVILIAN.getNameRole(), ""));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + nameRole);
        }
    }


    public PlayerList randomDistributionOfRole(UUID id) {

        PlayerList cloneList = new PlayerList();

        Room room = roomRepositories.getOne(id);

        int minPlayersInRoom = room.getMinSizePlayers();

        if(minPlayersInRoom <= completeGame.nameOfList.size()) {
            if(completeGame.nameOfList.size() > completeGame.listOfRole.size()) {
                int notEnoughPlayers = completeGame.nameOfList.size() - completeGame.listOfRole.size();
                for (int i = 0; i < notEnoughPlayers; i++) {
                    completeGame.listOfRole.add(playerFactory.createPlayer(RedPlayers.CIVILIAN.getNameRole(), ""));
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
