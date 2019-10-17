package com.scoliztur.game.mafia.services.game;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.entity.RoomPlayer;
import com.scoliztur.game.mafia.entity.repositories.RoomPlayerRepositories;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.logic.Murder;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.Don;
import com.scoliztur.game.mafia.logic.players.role.Mafia;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import com.scoliztur.game.mafia.services.factory.PlayerRoleBindingService;
import com.scoliztur.game.mafia.services.game.model.ChangeOfDayAndNight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompleteGame implements ChangeOfDayAndNight {

    @Autowired
    private RoomPlayerRepositories roomPlayerRepositories;

    @Autowired
    private UserRepositories userRepositories;

    private final PlayerRoleBindingService roleBindingService = new PlayerRoleBindingService();

    public List<String> nameOfList;
    public List<Player> listOfRole;
    public PlayerList playerList = new PlayerList();
    public OfferForKilling listForMafia;
    public OfferForKilling listForCivilian;
    private Murder murder = new Murder();
    private int countPlayer;
    private boolean isDay;

    @Override
    public void day() {
        isDay = true;
    }

    @Override
    public void night() {
        isDay = false;
    }

    @Override
    public boolean isDay() {
        return isDay;
    }

    public void newPlayerList() {
        playerList = new PlayerList();
    }

    public void newListForMafia() {
        listForMafia = new OfferForKilling();
    }

    public void newListForCivilian() {
        listForCivilian = new OfferForKilling();
        countPlayer = -1;
    }

    public String murderDay() {
        return murder.killFromSelected(listForCivilian);
    }

    public String murderNightForMafia() {
        return murder.killFromSelected(listForMafia);
    }


    public List<String> viewOrderPlayerList(List<Player> playerList) {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < playerList.size(); i++) {
            stringList.add("Player [" + i + "], name " + playerList.get(i).toString());
        }

        return stringList;
    }

    public String pickPlayerSelectionOrder(String playerName, int numberPlayer) {

        Player thisPlayer = findPlayerInList(playerName);

        if (thisPlayer != null && playerList.getPlayerList().get(countPlayer + 1) != null) {
            if (thisPlayer.equals(playerList.getPlayerList().get(countPlayer + 1))) {
                countPlayer++;
                String nextPlayer = "";
                try {
                    if (playerList.getPlayerList().get(numberPlayer) != null) {
                        playerList.getPlayerList().get(countPlayer)
                                .pick(listForCivilian, playerList.getPlayerList().get(numberPlayer), isDay());

                        if (countPlayer + 1 <= playerList.getPlayerList().size()) {
                            nextPlayer = playerList.getPlayerList().get(countPlayer + 1).getName() + " pick next!";
                        }

                        return playerList.getPlayerList().get(countPlayer).getName() + " picks " +
                                playerList.getPlayerList().get(numberPlayer).getName() + "\n" +
                                nextPlayer;
                    } else {
                        countPlayer++;
                        return "Player does not pick anyone";
                    }
                } catch (IndexOutOfBoundsException e) {
                    countPlayer++;
                    return "Player does not pick anyone";
                }
            } else {
                return "Such player none in the room";
            }
        } else {
            return "Such player none";
        }
    }

    public String vote(String playerName, int numberPlayer) {

        Player thisPlayer = findPlayerInList(playerName);

        if (thisPlayer != null) {
            if (playerList.getPlayerList().get(numberPlayer) != null) {
                return thisPlayer.vote(listForCivilian,
                        listForCivilian.getPlayerList().get(numberPlayer), isDay());
            } else {
                return "Such player none";
            }
        } else {
            return "Player does not vote";
        }
    }


    public String actionPlayerNight(String playerName, int numberPlayer) {

        Player thisPlayer = findPlayerInList(playerName);

        if (thisPlayer.toString().equals(BlackPlayers.MAFIA.getNameRole())) {
            Mafia mafia = (Mafia) thisPlayer;
            mafia.setOffer(listForMafia);
            return thisPlayer.action(playerList.getPlayerList().get(numberPlayer), isDay());
        } else if (thisPlayer.toString().equals(BlackPlayers.DON.getNameRole())) {
            Don don = (Don) thisPlayer;
            return thisPlayer.action(playerList.getPlayerList().get(numberPlayer), isDay())
                    + "\n" + don.findSheriff(playerList.getPlayerList().get(numberPlayer), isDay());
        } else {
            return thisPlayer.action(playerList.getPlayerList().get(numberPlayer), isDay());
        }
    }

    private String restoreTheGame(List<Player> players, boolean day) {

        playerList = new PlayerList();
        playerList.getPlayerList().addAll(players);

        isDay = day;

        return "Successful";
    }

    public String restore(List<RoomPlayer> roomPlayerList, boolean day) {

        List<Player> clonePlayerList = new ArrayList<>();

        for (int i = 0; i < roomPlayerList.size(); i++) {
            Player player = roleBindingService
                    .createPlayer(roomPlayerList.get(i).getNameRole(), roomPlayerList.get(i).getNickname());
            player.setActionDay(roomPlayerList.get(i).isActiveDay());
            player.setActionNight(roomPlayerList.get(i).isActiveNight());
            if(player.isAlive()) {
                clonePlayerList.add(player);
            }
        }

        return restoreTheGame(clonePlayerList, day);

    }

    public List<RoomPlayer> saveRoomPlayer(Room room) {

        List<RoomPlayer> list = new ArrayList<>();

        List<Player> clonePlayerList = new ArrayList<>(playerList.getPlayerList());

        for (Player player : clonePlayerList) {

            RoomPlayer roomPlayer;

            if(roomPlayerRepositories.existsRoomPlayerByNickname(player.getName())) {
                roomPlayer = roomPlayerRepositories.findByNickname(player.getName());
            } else {
                roomPlayer = new RoomPlayer();
                roomPlayer.setNickname(player.getName());
                roomPlayer.setNameRole(player.toString());
                roomPlayer.setRoomUser(room);
            }
            roomPlayer.setActiveDay(player.isActionDay());
            roomPlayer.setActiveNight(player.isActionNight());

            if(!player.isAlive()) {
                playerList.deletePlayer(player);
                roomPlayerRepositories.delete(roomPlayer);
                userRepositories.findUserByUsername(player.getName()).setRoomUser(null);
            } else {
                list.add(roomPlayer);
            }
        }

        return list;
    }

    public boolean isMafiaInRoom() {
        for (Player player: playerList.getPlayerList()) {
            for (BlackPlayers nameRole : BlackPlayers.values()) {
                if(nameRole.getNameRole().equals(player.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCivilianInRoom() {
        for (Player player: playerList.getPlayerList()) {
            for (RedPlayers nameRole : RedPlayers.values()) {
                if(nameRole.getNameRole().equals(player.toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Player findPlayerInList(String playerName) {
        Player thisPlayer = null;
        for (Player player: playerList.getPlayerList()) {
            if(player.getName().equals(playerName)) {
                thisPlayer = player;
            }
        }
        return thisPlayer;
    }
}
