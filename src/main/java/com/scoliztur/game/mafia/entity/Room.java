package com.scoliztur.game.mafia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "creator")
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID creatorId;

    @org.hibernate.annotations.Type(type="yes_no")
    @NotNull
    @Column(name = "is_day")
    private boolean day;

    @OneToMany(mappedBy = "roomUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RoomPlayer> playerList;

    @OneToMany(mappedBy = "roomUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<AppUser> appUsers;


    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", maxSizePlayers=" + maxSizePlayers +
                ", minSizePlayers=" + minSizePlayers +
                ", playersNow=" + playersNow +
                ", day=" + day +
                '}';
    }
}
