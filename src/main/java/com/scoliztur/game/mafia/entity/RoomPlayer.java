package com.scoliztur.game.mafia.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "room_player")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoomPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false, updatable = false)
    private Long playerId;

    @Column
    private String nickname;

    @Column
    private String nameRole;

    @Column
    private boolean alive;

    @Column
    private boolean activeDay;

    @Column
    private boolean activeNight;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Room roomUser;
}
