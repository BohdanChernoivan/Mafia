package com.scoliztur.game.mafia.entity;

import lombok.Data;

@Data
public class RoomPlayer {

    private String nickname;
    private String nameRole;
    private boolean alive;
    private boolean activeDay;
    private boolean activeNight;
}
