package com.scoliztur.game.mafia.security.roles;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleStatus implements GrantedAuthority {

    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
