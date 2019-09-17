package com.scoliztur.game.mafia.entity;

import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playerList")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "playerList", fetch = FetchType.LAZY, targetEntity = User.class)
//    private List<User> users;

}

