package com.aromero.videoserver.security.services;

import com.aromero.videoserver.security.jwt.JWTokenUtils;
import com.aromero.videoserver.services.user.model.Role;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AuthenticationUserManager implements ReactiveAuthenticationManager {

    private final JWTokenUtils jwTokenUtils;
    private final UserAuthService userAuthService;

    @Autowired
    public AuthenticationUserManager(JWTokenUtils jwTokenUtils, UserAuthService userAuthService) {
        this.jwTokenUtils = jwTokenUtils;
        this.userAuthService = userAuthService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username;
        try {
            username = jwTokenUtils.getUsernameFromToken(authToken);
        } catch (Exception e) {
            username = null;
        }

        UserDetails userAuth = this.userAuthService.loadUserByUsername(username);

        if (username != null && jwTokenUtils.validateToken(authToken, userAuth)) {
            Claims claims = jwTokenUtils.getAllClaimsFromToken(authToken);
            List<Map<String, String>> rolesMap = claims.get("roles", List.class);

            List<GrantedAuthority> authoritiesList = new LinkedList<>();
            rolesMap.forEach(authority -> authoritiesList.add(new SimpleGrantedAuthority(authority.get("authority"))));

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authoritiesList
            );
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}
