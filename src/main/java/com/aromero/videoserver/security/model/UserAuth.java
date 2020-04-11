package com.aromero.videoserver.security.model;

import com.aromero.videoserver.services.user.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
public class UserAuth implements UserDetails {

    private Long id;
    private String username;
    private String encryptedPassword;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserAuth build(User user) {
        UserAuth userAuth = new UserAuth();
        userAuth.id = user.getId();
        userAuth.username = user.getUsername();
        userAuth.encryptedPassword = user.getEncryptedPassword();
        List<GrantedAuthority> authoritiesList = new LinkedList<>();
        user.getRoles().forEach(rol -> authoritiesList.add(new SimpleGrantedAuthority(rol.getRole())));
        userAuth.authorities = authoritiesList;

        return userAuth;
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
