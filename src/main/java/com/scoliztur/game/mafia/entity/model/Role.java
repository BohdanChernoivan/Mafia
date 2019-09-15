package com.scoliztur.game.mafia.entity.model;

import com.scoliztur.game.mafia.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

}
