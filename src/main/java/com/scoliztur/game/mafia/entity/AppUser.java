package com.scoliztur.game.mafia.entity;

import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AppUser extends BaseEntity {

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "username", nullable = false, unique = true, length = 12)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    private List<RoleUser> roleUser;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name = "room_id")
    private Room roomUser;

}
