package com.scoliztur.game.mafia.services;

import com.scoliztur.game.mafia.filters.model.RoleStatus;
import com.scoliztur.game.mafia.filters.model.ApplicationUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = loadApplicationUserByUsername(username);
        return new User(applicationUser.getUsername(),
                applicationUser.getPassword(),
                AuthorityUtils.createAuthorityList(RoleStatus.PLAYER.getUserRole()));
    }

    public ApplicationUser loadApplicationUserByUsername(String username) {
        return new ApplicationUser(username, "");
    }
}
