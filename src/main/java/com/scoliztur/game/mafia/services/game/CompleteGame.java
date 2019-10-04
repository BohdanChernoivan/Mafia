package com.scoliztur.game.mafia.services.game;

import com.scoliztur.game.mafia.logic.Murder;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.services.game.model.ChangeOfDayAndNight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompleteGame implements ChangeOfDayAndNight {

    public List<String> nameOfList;
    public List<Player> listOfRole;
    public PlayerList playerList = new PlayerList();
    public OfferForKilling listForMafia;
    public OfferForKilling listForCivilian;
    public int countPlayer;
    private Murder murder = new Murder();
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
    }

    public String murderDay() {
        return murder.killFromSelected(listForCivilian, playerList);
    }

    public String murderNightForMafia() {
        return murder.killFromSelected(listForMafia, playerList);
    }


    public List<String> viewOrderPlayerList(List<Player> playerList) {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < playerList.size(); i++) {
            stringList.add("Player [" + i + "], name " + playerList.get(i).toString() + " = "
                    + playerList.get(i).getName());
        }

        return stringList;
    }

    public String pickPlayerSelectionOrder(int numberPlayer) {

        if (playerList.getPlayerList().get(countPlayer) != null) {
            countPlayer++;
            if (playerList.getPlayerList().get(numberPlayer) != null) {
                playerList.getPlayerList().get(countPlayer)
                        .pick(listForCivilian, playerList.getPlayerList().get(numberPlayer), isDay());

                return playerList.getPlayerList().get(countPlayer).getName() + " picks " +
                        playerList.getPlayerList().get(numberPlayer).getName();
            } else {
                countPlayer++;
                return "Player does not pick anyone";
            }
        } else {
            return "Such player none";
        }
    }

    public String vote(int thisNumberPlayer, int numberPlayer) {

        Player player = playerList.getPlayerList().get(thisNumberPlayer);

        if (player != null) {
            if (playerList.getPlayerList().get(numberPlayer) != null) {
                return player.vote(listForCivilian, listForCivilian.getPlayerList().get(numberPlayer), isDay());
            } else {
                return "Such player none";
            }
        } else {
            return "Player does not vote";
        }
    }


    public String actionPlayerNight(int thisNumberPlayer, int numberPlayer) {

        Player player = playerList.getPlayerList().get(thisNumberPlayer);

        if(player.toString().equals(BlackPlayers.MAFIA.getNameRole())) {
            player.action(playerList.getPlayerList().get(numberPlayer), isDay());
            return player.additionalAction(playerList.getPlayerList().get(numberPlayer), isDay(), listForMafia);
        } else {
            return player.action(playerList.getPlayerList().get(numberPlayer), isDay());
        }

    }
}
