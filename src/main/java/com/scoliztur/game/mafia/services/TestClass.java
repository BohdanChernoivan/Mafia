package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.logic.act_game.OfferForKilling;
import com.scoliztur.game.mafia.logic.players.role.*;

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


    }
}
