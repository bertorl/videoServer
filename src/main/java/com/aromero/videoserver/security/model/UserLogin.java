package com.aromero.videoserver.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class UserLogin {

    private Long id;
    private String username;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public UserLogin(Long id, String username, String token, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }
}
