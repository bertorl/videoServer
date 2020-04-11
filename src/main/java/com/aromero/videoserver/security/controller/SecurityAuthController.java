package com.aromero.videoserver.security.controller;

import com.aromero.videoserver.security.jwt.JWTokenUtils;
import com.aromero.videoserver.security.model.UserAuth;
import com.aromero.videoserver.security.model.UserLogin;
import com.aromero.videoserver.security.services.UserAuthService;
import com.aromero.videoserver.services.user.model.RolesEnum;
import com.aromero.videoserver.services.user.model.User;
import com.aromero.videoserver.services.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
public class SecurityAuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JWTokenUtils jwTokenUtils;
    private final UserAuthService userAuthService;

    @Autowired
    public SecurityAuthController(UserService userService, JWTokenUtils jwTokenUtils,
                                  PasswordEncoder passwordEncoder, UserAuthService userAuthService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwTokenUtils = jwTokenUtils;
        this.userAuthService = userAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        user.getRoles().forEach(rol -> {
            switch (rol.getRole()){
                case "admin":
                    rol.setRole(RolesEnum.ADMIN.getValue());
                    break;
                case "user":
                    rol.setRole(RolesEnum.USER.getValue());
                    break;
            }
        });

        user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        User result = this.userService.saveUser(user);

        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public  Mono<ResponseEntity<UserLogin>>login(@RequestBody User user) {

        UserAuth userAuth = (UserAuth) this.userAuthService.loadUserByUsername(user.getUsername());

        if(userAuth.getEncryptedPassword().equals(this.passwordEncoder.encode(user.getEncryptedPassword()))){
            String token = jwTokenUtils.generateToken(userAuth);
            UserLogin userToken = new UserLogin(userAuth.getId(), userAuth.getUsername(), token, userAuth.getAuthorities());
            return  Mono.just(new ResponseEntity<>(userToken, HttpStatus.OK));
        } else {
            return Mono.just(new ResponseEntity<>(HttpStatus.FORBIDDEN));
        }
    }
}
