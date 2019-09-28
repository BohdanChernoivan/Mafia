package com.scoliztur.game.mafia.security.roles;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleStatus implements GrantedAuthority {

    PLAYER("PLAYER"),
    ADMIN("ADMIN");

    private String userRole;

    RoleStatus(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getAuthority() {
        return userRole;
    }
}
