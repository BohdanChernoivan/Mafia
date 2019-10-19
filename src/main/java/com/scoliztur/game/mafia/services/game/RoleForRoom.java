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
    private final Game game;


    public RoleForRoom(RoomRepositories roomRepositories, PlayerRoleBindingService playerFactory, Game game) {
        this.roomRepositories = roomRepositories;
        this.playerFactory = playerFactory;
        this.game = game;
    }

    public void addRole(String nameRole) {

        switch (nameRole) {
            case "Don":
                game.listOfRole.add(playerFactory.createPlayer(BlackPlayers.DON.getNameRole(), ""));
                break;
            case "Mafia":
                game.listOfRole.add(playerFactory.createPlayer(BlackPlayers.MAFIA.getNameRole(), ""));
                break;
            case "Courtesan":
                game.listOfRole.add(playerFactory.createPlayer(BlackPlayers.COURTESAN.getNameRole(), ""));
                break;
            case "Sheriff":
                game.listOfRole.add(playerFactory.createPlayer(RedPlayers.SHERIFF.getNameRole(), ""));
                break;
            case "Barman":
                game.listOfRole.add(playerFactory.createPlayer(RedPlayers.BARMAN.getNameRole(), ""));
                break;
            case "Doctor":
                game.listOfRole.add(playerFactory.createPlayer(RedPlayers.DOCTOR.getNameRole(), ""));
                break;
            case "Civilian" :
                game.listOfRole.add(playerFactory.createPlayer(RedPlayers.CIVILIAN.getNameRole(), ""));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + nameRole);
        }
    }


    public PlayerList randomDistributionOfRole(UUID id) {

        PlayerList cloneList = new PlayerList();

        Room room = roomRepositories.getOne(id);

        int minPlayersInRoom = room.getMinSizePlayers();

        if(minPlayersInRoom <= game.nameOfList.size()) {
            if(game.nameOfList.size() > game.listOfRole.size()) {
                int notEnoughPlayers = game.nameOfList.size() - game.listOfRole.size();
                for (int i = 0; i < notEnoughPlayers; i++) {
                    game.listOfRole.add(playerFactory.createPlayer(RedPlayers.CIVILIAN.getNameRole(), ""));
                }
            }
        } else {
            throw new RuntimeException("Not enough players");
        }

        Collections.shuffle(game.listOfRole);

        for (Player player : game.listOfRole) {
            insertRndUsername(player, game.nameOfList, cloneList);
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
