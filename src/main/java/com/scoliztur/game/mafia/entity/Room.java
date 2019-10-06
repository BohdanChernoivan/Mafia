package com.scoliztur.game.mafia.entity;

import com.scoliztur.game.mafia.entity.model.BaseEntity;
import com.scoliztur.game.mafia.logic.players.basic.Player;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Room extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Transient
    @ElementCollection
    private List<RoomPlayer> playerList = new ArrayList<>();

    @Column(name = "max_size_players", nullable = false)
    private int maxSizePlayers;

    @Column(name = "min_size_players", nullable = false)
    private int minSizePlayers;

    @Column(name = "players_now")
    private int playersNow;

    @OneToMany(mappedBy = "roomUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AppUser> appUsers;

    public void addUser(AppUser appUser) {
        appUser.setRoomUser(this);
    }

    public void addPlayer(RoomPlayer player) {
        this.playerList.add(player);
    }

    public void addPlayerNow() {
        playersNow++;
    }
}
