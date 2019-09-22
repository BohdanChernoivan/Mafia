package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.entity.Room;
import com.scoliztur.game.mafia.logic.players.PlayerList;
import com.scoliztur.game.mafia.logic.Murder;
import com.scoliztur.game.mafia.logic.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.*;
import com.scoliztur.game.mafia.logic.players.role.factory.RolePlayerFactory;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;

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

        List<Player> playerArrayList = new ArrayList<>();
        List<String> nameLinkedList = new LinkedList<>();
        nameLinkedList.add("VANY");
        nameLinkedList.add("DSA");
        nameLinkedList.add("dsada");
        nameLinkedList.add("dsadsadaf");
        nameLinkedList.add("ds123adaf");
        nameLinkedList.add("dsa546daf");

        playerArrayList.add(sheriff);
        playerArrayList.add(mafia);
        playerArrayList.add(barman);

        System.out.println("HAVAT");

        PlayerList playerList1 = randomDistributionOfRole(nameLinkedList, playerArrayList);

        for (Player player: playerList1.getPlayerList()) {
            System.out.println("NAME = " + player.getName() + " ROLE = " + player.toString());
        }

    }

    private static PlayerList randomDistributionOfRole(List<String> users, List<Player> listOfRole) {

        PlayerList cloneList = new PlayerList();

        int minPlayersInRoom = 5;

        if(minPlayersInRoom > listOfRole.size()) {
            int notEnoughPlayers = minPlayersInRoom - listOfRole.size();
            for (int i = 0; i < notEnoughPlayers; i++) {
                listOfRole.add(new RolePlayerFactory().createRedPlayer(RedPlayers.CIVILIAN, ""));
            }
        }


        Collections.shuffle(listOfRole);


        for (Player player : listOfRole) {
            call(player, users, cloneList);
        }

        return cloneList;
    }

    public static void call(Player player, List<String> strings, PlayerList playerList) {

        int random1 = new Random().nextInt(strings.size());
        player.setName(strings.get(random1));
        playerList.insertPlayer(player);
        strings.remove(strings.get(random1));
    }
}
