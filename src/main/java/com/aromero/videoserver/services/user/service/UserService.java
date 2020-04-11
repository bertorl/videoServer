package com.aromero.videoserver.services.user.service;

import com.aromero.videoserver.services.user.model.User;

import java.util.List;

public interface UserService {
    public User getUser(String username);
    public User saveUser(User user);
    public List<User> getAllUsers();
}
