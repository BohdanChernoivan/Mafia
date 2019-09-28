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
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) {
        AppUser appUser = userRepositories.findUserByUsername(name);

        if(appUser == null) {
            throw new UsernameNotFoundException(name);
        }

        log.info("In loadUserByUsername - appUser: {} loaded by username: {}", appUser, name);

        return new UserPrincipal(appUser);
    }

}
