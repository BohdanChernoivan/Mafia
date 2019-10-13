package com.scoliztur.game.mafia.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @Column(name = "name", nullable = false, unique = true)
    private String nickname;

    @Column(name = "role")
    private String nameRole;

    @org.hibernate.annotations.Type(type="yes_no")
    @NotNull
    @Column(name = "day_active")
    private boolean activeDay;

    @org.hibernate.annotations.Type(type="yes_no")
    @NotNull
    @Column(name = "night_active")
    private boolean activeNight;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Room roomUser;
}
