package com.scoliztur.game.mafia.entity;

import com.scoliztur.game.mafia.entity.model.BaseEntity;
import com.scoliztur.game.mafia.logic.players.role.type.BlackPlayers;
import com.scoliztur.game.mafia.logic.players.role.type.RedPlayers;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "roomUser", cascade = CascadeType.ALL, targetEntity = User.class)
    private Set<User> users;
}
