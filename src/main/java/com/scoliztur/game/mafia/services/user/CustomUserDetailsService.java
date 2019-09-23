package com.scoliztur.game.mafia.services.user;

import com.scoliztur.game.mafia.entity.AppUser;
import com.scoliztur.game.mafia.entity.repositories.UserRepositories;
import com.scoliztur.game.mafia.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = userRepositories.findUserByUsername(username);

        if(appUser == null) {
            throw new UsernameNotFoundException(username);
        }

        log.info("In loadUserByUsername - appUser: {} loaded by username: {}", appUser, username);

        return new UserPrincipal(appUser);
    }

}
