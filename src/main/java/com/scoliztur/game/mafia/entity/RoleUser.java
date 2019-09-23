package com.scoliztur.game.mafia.entity;

import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roleUser")
@Getter
@Setter
@ToString
public class RoleUser extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roleUser", fetch = FetchType.LAZY)
    private List<AppUser> appUsers;

}

