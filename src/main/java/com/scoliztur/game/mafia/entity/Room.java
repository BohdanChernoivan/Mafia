package com.scoliztur.game.mafia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "max_size_players", nullable = false)
    private int maxSizePlayers;

    @Column(name = "min_size_players", nullable = false)
    private int minSizePlayers;

    @Column(name = "players_now")
    private int playersNow;

    @Column(name = "is_day")
    private boolean day;

    @OneToMany(mappedBy = "roomUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RoomPlayer> playerList;

    @OneToMany(mappedBy = "roomUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<AppUser> appUsers;

}
