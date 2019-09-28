package com.scoliztur.game.mafia.filters;

import com.scoliztur.game.mafia.security.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.stream.Collectors;

import static com.scoliztur.game.mafia.security.SecurityConstants.*;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl(AUTH_LOGIN_URL);
    }

    @Override
    // {"username": "name", "password": "pass"}
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        var username = request.getHeader("login");
        var password = request.getHeader("password");
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password);

        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {

        log.info("ds {}", authentication.getPrincipal());

        UserPrincipal user = (UserPrincipal) authentication
                .getPrincipal();

        var roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var signingKey = JWT_SECRET.getBytes();

        var token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("username", user.getUsername())
                .claim("rol", roles)
                .compact();

        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + token);
    }
}
