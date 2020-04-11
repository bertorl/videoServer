package com.aromero.videoserver.security.services;

import com.aromero.videoserver.security.model.UserAuth;
import com.aromero.videoserver.services.user.model.User;
import com.aromero.videoserver.services.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(UserAuthService.BEAN_NAME)
public class UserAuthService implements UserDetailsService {

    public static final String BEAN_NAME = "UserAuthService";

    private final UserService userService;

    @Autowired
    public UserAuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUser(username);

        if(user.getUsername().equals(username)) {
            return UserAuth.build(user);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
