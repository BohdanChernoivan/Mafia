package com.scoliztur.game.mafia.services.game;

import com.scoliztur.game.mafia.logic.Murder;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.basic.Player;
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

    public void newListForMaffiozi() {
        listForMafia = new OfferForKilling();
    }

    public void newListForCivilian() {
        listForCivilian = new OfferForKilling();
    }

    public void murderDay() {
        murder.killFromSelected(listForCivilian, playerList);
    }

    public void murderNightForMaffiozi() {
        murder.killFromSelected(listForMafia, playerList);
    }


    public List<String> viewOrderPlayerList(List<Player> playerList) {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < playerList.size(); i++) {
            stringList.add("Player [" + i + "], name " + playerList.get(i).toString() + " = "
                    + playerList.get(i).getName());
        }

        return stringList;
    }

    public String pickSelectionOrder(int numberPlayer) {

        if (playerList.getPlayerList().get(numberPlayer) != null) {
            countPlayer++;
            if (playerList.getPlayerList().get(countPlayer) != null) {
                playerList.getPlayerList().get(countPlayer)
                        .pick(listForCivilian, playerList.getPlayerList().get(numberPlayer), isDay());

                return playerList.getPlayerList().get(countPlayer).getName() + " picks " +
                        playerList.getPlayerList().get(numberPlayer).getName();
            }
            return "Such player none";
        }
        return "Player does not pick anyone";
    }

    public String voteSelectionOrder(int numberPlayer) {

        if (listForCivilian.getPlayerList().get(numberPlayer) != null) {
            countPlayer++;
            if (playerList.getPlayerList().get(countPlayer) != null) {
                playerList.getPlayerList().get(countPlayer)
                        .vote(listForCivilian, listForCivilian.getPlayerList().get(numberPlayer), isDay());

                return playerList.getPlayerList().get(countPlayer).getName() + " voted for " +
                        listForCivilian.getPlayerList().get(numberPlayer).getName();
            }
            return "Such player none";
        }
        return "Player does not voteSelectionOrder";

    }
}
