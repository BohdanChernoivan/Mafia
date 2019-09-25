package com.scoliztur.game.mafia.entity;

import com.scoliztur.game.mafia.entity.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleUser extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roleUser", fetch = FetchType.LAZY)
    private List<AppUser> appUsers;

}
