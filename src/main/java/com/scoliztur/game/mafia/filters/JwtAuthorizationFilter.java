package com.scoliztur.game.mafia.filters;

import com.scoliztur.game.mafia.filters.model.ApplicationUser;
import com.scoliztur.game.mafia.services.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.scoliztur.game.mafia.security.SecurityConstants.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        var authentication = getAuthentication(request);

        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(TOKEN_HEADER);

        if (!StringUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            try {
                var signingKey = JWT_SECRET.getBytes();

                var parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));

                var username = parsedToken
                        .getBody()
                        .getSubject();

//                var authorities = ((List<?>) parsedToken.getBody()
//                        .get("rol")).stream()
//                        .map(authority -> new SimpleGrantedAuthority((String) authority))
//                        .collect(Collectors.toList());

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                ApplicationUser applicationUser = customUserDetailsService.loadApplicationUserByUsername(username);

                if (!StringUtils.isEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities());
                }
            } catch (SignatureException exception) {
                log.warn("JWT signature does not match locally computed signature : {} failed : {}", token, exception.getMessage());
            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
            }
        }

        return null;
    }
}
