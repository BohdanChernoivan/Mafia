package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.Murder;
import com.scoliztur.game.mafia.logic.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.*;

import java.util.*;

public class TestClass {
    public static void main(String[] args) {

        boolean day = true;
        OfferForKilling listForMafia;
        OfferForKilling listForCivilian;

        Civilian civilian = new Civilian("Player1");
        Courtesan courtesan = new Courtesan("Player2");
        Barman barman = new Barman("Player3");
        Civilian civilian1 = new Civilian("Player4");
        Doctor doctor = new Doctor("Player5");
        Don don = new Don("Player6");
        Mafia mafia = new Mafia("Player7");
        Sheriff sheriff = new Sheriff("Player8");



        PlayerList playerList = new PlayerList();

        playerList.insertPlayer(civilian);
        playerList.insertPlayer(courtesan);
        playerList.insertPlayer(barman);
        playerList.insertPlayer(civilian1);
        playerList.insertPlayer(doctor);
        playerList.insertPlayer(don);
        playerList.insertPlayer(mafia);
        playerList.insertPlayer(sheriff);

        for (Player item : playerList.getPlayerList()) {
            System.out.println(item.getName());
        }

        listForCivilian = new OfferForKilling();
        courtesan.pick(listForCivilian, barman, day);
        doctor.pick(listForCivilian, courtesan, day);
        mafia.pick(listForCivilian, sheriff, day);

        System.out.println();

        for (Map.Entry<Player, Byte> entry : listForCivilian.getPlayerByteMap().entrySet()) {
            System.out.println(entry.getKey().getName());
        }

        Random r = new Random();


        for (Player value : playerList.getPlayerList()) {
            int s = r.nextInt(listForCivilian.getPlayerByteMap().size());

            System.out.println(value.vote(listForCivilian, listForCivilian.getPlayerList().get(s), day));
        }

        for (Map.Entry<Player, Byte> entry : listForCivilian.getPlayerByteMap().entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue());
        }


        System.out.println(Murder.killFromSelected(listForCivilian, playerList));

        for (Map.Entry<Player, Byte> entry : listForCivilian.getPlayerByteMap().entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getKey().isAlive());
        }

        for (Player player : playerList.getPlayerList()) {
            System.out.println(player.getName());
        }

        day = false;

        listForMafia = new OfferForKilling();

        System.out.println(barman.poison(mafia, day));

        System.out.println(courtesan.confuse(courtesan, day));

        System.out.println(sheriff.findMafia(mafia, day));

        System.out.println(mafia.offerKillNight(sheriff, day, listForMafia));

        System.out.println(doctor.resurrect(courtesan, day));

        System.out.println(listForMafia.getPlayerByteMap().size());
    }
}
