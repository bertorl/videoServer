package com.aromero.videoserver.services.user.controller;

import com.aromero.videoserver.services.user.model.User;
import com.aromero.videoserver.services.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    //@PreAuthorize()
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(this.userService.getAllUsers());
    }


}
