package com.scoliztur.game.mafia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scoliztur.game.mafia.entity.model.BaseEntity;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Room extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Transient
    @Column(name = "blackPlayers")
    private List<BlackPlayers> blackPlayers;

    @Transient
    @Column(name = "redPlayers")
    private List<RedPlayers> redPlayers;

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

    public void addPlayerNow() {
        playersNow++;
    }
}
